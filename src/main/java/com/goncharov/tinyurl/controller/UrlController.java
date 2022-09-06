package com.goncharov.tinyurl.controller;

import com.goncharov.tinyurl.dto.InfoDTO;
import com.goncharov.tinyurl.dto.RequestDTO;
import com.goncharov.tinyurl.entity.Url;
import com.goncharov.tinyurl.util.UrlMapper;
import com.goncharov.tinyurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

import static com.goncharov.tinyurl.util.ExceptionUtil.returnExceptionsToClient;

@RestController
@RequestMapping("/tinyurl")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody @Valid RequestDTO requestDto,
                                               BindingResult bindingResult) {

        // throws UrlNotCreatedException in static method with exception messages
        if (bindingResult.hasErrors()) {
            returnExceptionsToClient(bindingResult);
        }

        Url urlToAlias = UrlMapper.INSTANCE.urlToAlias(requestDto);

        urlService.createUrl(urlToAlias);

        return new ResponseEntity<>(UrlMapper.INSTANCE.response(urlToAlias), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{alias}")
    public ResponseEntity<HttpStatus> redirectToOriginalUrl(@PathVariable String alias,
                                                            HttpServletResponse response) throws IOException {

        urlService.sendRedirect(alias, response);

        return ResponseEntity.ok(HttpStatus.MOVED_PERMANENTLY);
    }

    @DeleteMapping("/delete/{alias}")
    public ResponseEntity<HttpStatus> deleteAlias(@PathVariable String alias) {

        urlService.deleteByAlias(alias);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{alias}/info")
    public ResponseEntity<?> getInfo(@PathVariable String alias) {

        InfoDTO info = UrlMapper.INSTANCE.info(urlService.getUrlFromAlias(alias));

        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
