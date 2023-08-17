package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.entity.Clinic;

import java.util.List;

public interface ClinicService {
    public List<Clinic> getClinics();

    public Clinic findUserByName(String theName);

}
