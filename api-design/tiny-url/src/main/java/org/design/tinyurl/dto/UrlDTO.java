package org.design.tinyurl.dto;

import lombok.Value;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Value
public class UrlDTO {
    @NotNull
    private String originalUrl;
    @Size(max = 8)
    private String urlHash;
    private ZonedDateTime expiryDate;
    @NotNull
    private Long userId;

}
