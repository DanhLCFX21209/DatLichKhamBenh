package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Patient;
import com.spring.security.jwtbasic.entity.Role;
import com.spring.security.jwtbasic.entity.User;

public interface UserDao {

    public User findByEmail(String email);
    public void saveUser(User user);
    public User findUserByName(String theName);

    public User findById(int theId);



}
