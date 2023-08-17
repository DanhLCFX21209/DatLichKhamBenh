package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Specialization;
import com.spring.security.jwtbasic.entity.User;

import java.util.List;

public interface SpecializationDao {
    public List<Specialization> getSpecializations();

    public Specialization findSpecializationByName(String theName);

}
