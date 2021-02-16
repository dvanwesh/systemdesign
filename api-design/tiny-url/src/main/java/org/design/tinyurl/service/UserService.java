package org.design.tinyurl.service;

import java.time.ZonedDateTime;

public interface UserService {
    void createUser(String name, String email, ZonedDateTime dob, Integer zipCode);
}
