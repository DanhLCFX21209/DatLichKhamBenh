package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.dao.DoctorUserDao;
import com.spring.security.jwtbasic.entity.DoctorUser;
import com.spring.security.jwtbasic.util.SearcBySpecializationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorUserServiceImpl implements DoctorUserService{
    @Autowired
    private DoctorUserDao doctorUserDao;

    @Override
    @Transactional
    public List<SearcBySpecializationDTO> findByDoctorUserName(String name) {
        List<DoctorUser> searchResults = doctorUserDao.findByDoctorUserName(name);
        List<SearcBySpecializationDTO> resultDTOs = new ArrayList<>();
        for (DoctorUser doctorUser : searchResults) {
            SearcBySpecializationDTO resultDTO = new SearcBySpecializationDTO();
            resultDTO.setDoctorName(doctorUser.getDoctor().getName());
            resultDTO.setSpecializationName(doctorUser.getSpecialization().getName());
            resultDTOs.add(resultDTO);
        }
        return  resultDTOs;
    }

//    @Override
//    @Transactional
//    public List<SearchDTO> findAll(String name) {
//        List<DoctorUser> search = doctorUserDao.findByDoctorUserName(name);
//        List<SearchDTO> searchDTOS = new ArrayList<>();
//        for (DoctorUser doctorUser : search) {
//            SearchDTO searchDTO = new SearchDTO();
//            searchDTO.setPlace(doctorUser.getPlace().getName());
//            searchDTO.setClinic(doctorUser.getClinic().getName());
//            searchDTO.setPrice(doctorUser.getClinic().getPrice());
//            searchDTO.setSpecialization(doctorUser.getSpecialization().getName());
//            searchDTOS.add(searchDTO);
//        }
//        return searchDTOS;
//    }


    @Override
    @Transactional
    public List<DoctorUser> findAll(String name) {
        return doctorUserDao.findAll(name);
    }

    @Override
    @Transactional
    public void saveDoctor(DoctorUser doctorUser) {
        doctorUserDao.saveDoctor(doctorUser);
    }
}
