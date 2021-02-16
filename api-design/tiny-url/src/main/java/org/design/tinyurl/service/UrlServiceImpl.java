package org.design.tinyurl.service;

import lombok.extern.java.Log;
import org.apache.catalina.connector.Response;
import org.bouncycastle.util.encoders.Hex;
import org.design.tinyurl.exception.TinyUrlErrorEnum;
import org.design.tinyurl.exception.TinyUrlServiceException;
import org.design.tinyurl.model.Url;
import org.design.tinyurl.model.User;
import org.design.tinyurl.repo.UrlRepository;
import org.design.tinyurl.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Log
public class UrlServiceImpl implements UrlService {
    private static final String ALGORITHM = "md5";
    private UrlRepository urlRepository;
    private UserRepository userRepository;
    private static Base64.Encoder encoder = Base64.getEncoder();

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createAliasUrl(String alias, String url, ZonedDateTime expiryTime, Long userId)
        throws TinyUrlServiceException {
        try {
            if (!Objects.isNull(alias) && urlRepository.findById(alias).isPresent()) {
                throw new TinyUrlServiceException(TinyUrlErrorEnum.ALIAS_NOT_AVAILABLE);
            }
            User user = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("No such user exists"));
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            sb.append(user.getId());
            if (Objects.isNull(alias)) {
                String hash = Hex.toHexString(MessageDigest.getInstance(ALGORITHM)
                    .digest(sb.toString().getBytes()));
                alias = encoder.encodeToString(hash.getBytes());
            }
            urlRepository.save(new Url(alias, url, expiryTime, user));
            return alias;
        } catch (NoSuchAlgorithmException ex) {
            log.severe("Unknown algorithm for hashing: " + ALGORITHM + ex);
            throw new TinyUrlServiceException(TinyUrlErrorEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteAlias(String alias) {
        urlRepository.deleteById(alias);
    }

    @Override
    public String redirectToOriginalUrl(String alias) throws TinyUrlServiceException {
        log.info("alias: "+alias);
        Optional<Url> url = urlRepository.findById(alias);
        if (!url.isPresent()) {
            throw new NoSuchElementException("No such alias exists");
        }
        return url.get().getUrl();
    }
}
