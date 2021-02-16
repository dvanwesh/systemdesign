package org.design.pastebin.dto;

import java.time.ZonedDateTime;

public class PasteDTO {
    private String content;
    private String custom_url;
    private String paste_name;
    private ZonedDateTime expiry_dt;
}
