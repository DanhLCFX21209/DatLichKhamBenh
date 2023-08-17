package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.dao.RoleDao;
import com.spring.security.jwtbasic.dao.UserDao;
import com.spring.security.jwtbasic.entity.Role;
import com.spring.security.jwtbasic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public User findByEmail(String email) {
        // check the database if the user already exists
        return userDao.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
        return Arrays.asList(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        // assign user details to the user object
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Tạo đối tượng Role
        Role role = roleDao.findRoleByName("ROLE_EMPLOYEE");
        user.setRole(role);

        // save user in the database
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User findUserByName(String theName) {
        return userDao.findUserByName(theName);
    }

    @Override
    @Transactional
    public User findById(int theId) {
        return userDao.findById(theId);
    }

    @Override
    @Transactional
    public void saveDoctor(User doctor) {
        // assign user details to the user object
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));

        // Tạo đối tượng Role
        Role role = roleDao.findRoleByName("ROLE_MANAGER");
        doctor.setRole(role);

        // save user in the database
        userDao.saveUser(doctor);
    }
}
