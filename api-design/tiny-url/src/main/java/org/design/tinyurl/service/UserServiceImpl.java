package org.design.tinyurl.service;

import org.design.tinyurl.model.User;
import org.design.tinyurl.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(String name, String email, ZonedDateTime dob, Integer zipCode) {
        userRepository.save(new User(name, email, dob, zipCode));
    }
}
