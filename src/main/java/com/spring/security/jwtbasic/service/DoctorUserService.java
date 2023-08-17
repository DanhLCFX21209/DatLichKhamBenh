package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.entity.DoctorUser;
import com.spring.security.jwtbasic.util.SearcBySpecializationDTO;

import java.util.List;

public interface DoctorUserService {
    public List<SearcBySpecializationDTO> findByDoctorUserName(String name);

//    public List<SearchDTO> findAll(String name);
    public List<DoctorUser> findAll(String name);

    public void saveDoctor(DoctorUser doctorUser);


}
