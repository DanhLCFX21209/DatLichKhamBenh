package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.entity.Specialization;

import java.util.List;

public interface SpecializationService {
    public List<Specialization> getSpecializations();

    public Specialization findSpecializationByName(String theName);


}
