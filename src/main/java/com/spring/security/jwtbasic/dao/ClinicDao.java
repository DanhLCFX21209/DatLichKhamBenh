package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Clinic;
import com.spring.security.jwtbasic.entity.User;

import java.util.List;

public interface ClinicDao {
    public List<Clinic> getClinics();

    public Clinic findUserByName(String theName);

}
