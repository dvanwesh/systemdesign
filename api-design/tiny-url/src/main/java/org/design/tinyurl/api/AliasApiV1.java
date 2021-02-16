package org.design.tinyurl.api;

import org.design.tinyurl.dto.UrlDTO;
import org.design.tinyurl.dto.UserDTO;
import org.design.tinyurl.exception.TinyUrlServiceException;

public interface AliasApiV1 {
    String createShortUrl(UrlDTO urlDTO) throws TinyUrlServiceException;

    String redirectUrl(String alias) throws TinyUrlServiceException;
}
