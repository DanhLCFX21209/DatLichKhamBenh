package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.dao.SpecializationDao;
import com.spring.security.jwtbasic.entity.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService{

    @Autowired
    private SpecializationDao specializationDao;

    @Override
    @Transactional
    public List<Specialization> getSpecializations() {
        return specializationDao.getSpecializations();
    }

    @Override
    @Transactional
    public Specialization findSpecializationByName(String theName) {
        return specializationDao.findSpecializationByName(theName);
    }
}
