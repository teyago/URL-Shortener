package com.goncharov.tinyurl.service;

import com.goncharov.tinyurl.entity.Url;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public interface UrlService {
    Url getUrlFromAlias(String alias);

    void createUrl(Url url);

    void saveUrl(Url url);

    void sendRedirect(String alias, HttpServletResponse response) throws IOException;

    void deleteByAlias(String alias);

    void deleteAllByExpirationDateBefore(LocalDateTime localDateTime);
}
