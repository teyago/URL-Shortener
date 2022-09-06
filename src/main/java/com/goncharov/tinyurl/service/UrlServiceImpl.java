package com.goncharov.tinyurl.service;

import com.goncharov.tinyurl.entity.Url;

import com.goncharov.tinyurl.exception.UrlDoesntExistException;
import com.goncharov.tinyurl.repository.UrlRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public void createUrl(Url url) {

        url.setCreationDate(LocalDateTime.now());
        url.setAlias(encodeUrl(url.getUrl()));
        url.setExpirationDate(setDate(url.getExpirationDate(), url.getCreationDate()));

        saveUrl(url);
    }

    @Transactional
    @Override
    public void saveUrl(Url url) {
        urlRepository.save(url);
    }

    @Override
    public Url getUrlFromAlias(String alias) {
        return urlRepository.findByAlias(alias).orElseThrow(UrlDoesntExistException::new);
    }

    @Override
    public void sendRedirect(String alias, HttpServletResponse response) throws IOException {

        Url url = urlRepository.findByAlias(alias).orElseThrow(UrlDoesntExistException::new);

        incrementCounter(url);

        response.sendRedirect(url.getUrl());
    }

    @Transactional
    @Override
    public void deleteAllByExpirationDateBefore(LocalDateTime localDateTime) {
        urlRepository.deleteAllByExpirationDateBefore(localDateTime);
    }

    @Transactional
    @Override
    public void deleteByAlias(String alias) {
        urlRepository.findByAlias(alias).orElseThrow(UrlDoesntExistException::new);
        urlRepository.deleteByAlias(alias);
    }

    @Transactional
    public void incrementCounter(Url url) {
        url.setNumberOfClicks(url.getNumberOfClicks() + 1);
        urlRepository.save(url);
    }

    private LocalDateTime setDate(LocalDateTime expirationDate, LocalDateTime creationDate) {
        return expirationDate == null ? creationDate.plusWeeks(1) : expirationDate;
    }

    private String encodeUrl(String url) {
        return Hashing.murmur3_32_fixed()
                .hashString(url.concat(LocalDateTime.now().toString()), StandardCharsets.UTF_8)
                .toString();
    }
}
