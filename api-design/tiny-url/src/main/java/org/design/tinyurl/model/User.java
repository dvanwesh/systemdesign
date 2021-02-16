package org.design.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.cglib.core.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 2000)
    private String name;

    @Column(length = 2000)
    private String email;

    @Column
    private ZonedDateTime dateOfBirth;

    @Column
    private ZonedDateTime createdDate;

    @Column
    private ZonedDateTime lastLogin;

    @Column
    private int zipCode;

    public User(String name, String email, ZonedDateTime dateOfBirth, int zipCode) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.zipCode = zipCode;
        this.lastLogin = ZonedDateTime.now();
        this.createdDate = ZonedDateTime.now();
    }

    protected User() {}
}
