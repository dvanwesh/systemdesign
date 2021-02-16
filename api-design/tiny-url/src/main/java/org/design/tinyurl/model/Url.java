package org.design.tinyurl.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
public class Url {
    @Id
    private String alias;

    @Column
    private String url;

    @Column
    private ZonedDateTime expiryTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Url(String alias, String url, ZonedDateTime expiryTime, User user) {
        this.alias = alias;
        this.url = url;
        this.expiryTime = expiryTime;
        this.user = user;
    }

    protected Url() {}
}
