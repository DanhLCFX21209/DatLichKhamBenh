package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.DoctorUser;
import com.spring.security.jwtbasic.entity.User;

import java.util.List;

public interface DoctorUserDao {
    public List<DoctorUser> findByDoctorUserName(String name);

    public List<DoctorUser> findAll(String name);

    public void saveDoctor(DoctorUser doctorUser);


}
