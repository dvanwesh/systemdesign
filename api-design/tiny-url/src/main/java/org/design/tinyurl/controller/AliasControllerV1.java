package org.design.tinyurl.controller;

import lombok.extern.java.Log;
import org.design.tinyurl.api.AliasApiV1;
import org.design.tinyurl.dto.UrlDTO;
import org.design.tinyurl.exception.TinyUrlServiceException;
import org.design.tinyurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/alias")
@Log
public class AliasControllerV1 implements AliasApiV1 {

    private UrlService urlService;

    @Autowired
    public AliasControllerV1(UrlService urlService) {
        this.urlService = urlService;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createShortUrl(@RequestBody @Validated UrlDTO urlDto)
        throws TinyUrlServiceException {
        return urlService.createAliasUrl(urlDto.getUrlHash(), urlDto.getOriginalUrl(),
            urlDto.getExpiryDate(), urlDto.getUserId());
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, path = "/{alias}")
    @ResponseStatus(HttpStatus.TEMPORARY_REDIRECT)
    public String redirectUrl(@PathVariable(value = "alias") String alias) throws TinyUrlServiceException {
        String url = urlService.redirectToOriginalUrl(alias);
        return "redirect:" + url;
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
