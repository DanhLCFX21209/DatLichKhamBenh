package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.dao.ClinicDao;
import com.spring.security.jwtbasic.dao.SpecializationDao;
import com.spring.security.jwtbasic.entity.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService{
    @Autowired
    private ClinicDao clinicDao;

    @Override
    @Transactional
    public List<Clinic> getClinics() {
        return clinicDao.getClinics();
    }

    @Override
    @Transactional
    public Clinic findUserByName(String theName) {
        return clinicDao.findUserByName(theName);
    }
}
