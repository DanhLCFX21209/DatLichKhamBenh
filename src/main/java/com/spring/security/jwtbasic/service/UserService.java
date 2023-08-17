package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByEmail(String email);

    public void saveUser(User user);

    public void saveDoctor(User doctor);

    public User findUserByName(String theName);

    public User findById(int theId);


}

