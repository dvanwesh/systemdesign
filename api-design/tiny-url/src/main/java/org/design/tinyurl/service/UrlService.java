package org.design.tinyurl.service;

import org.design.tinyurl.exception.TinyUrlServiceException;

import java.time.ZonedDateTime;

public interface UrlService {
    String createAliasUrl(String alias, String url, ZonedDateTime expiryTime, Long userId) throws TinyUrlServiceException;

    void deleteAlias(String alias);

    String redirectToOriginalUrl(String alias) throws TinyUrlServiceException;
}
