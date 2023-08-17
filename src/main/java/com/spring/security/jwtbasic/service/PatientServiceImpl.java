package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.dao.PatientDao;
import com.spring.security.jwtbasic.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientDao patientDao;

    @Override
    public List<Patient> getPatients() {
        return patientDao.getPatients();
    }

    @Override
    @Transactional
    public void savePatient(Patient patient) {
        patientDao.savePatient(patient);
    }

    @Override
    @Transactional
    public Patient findById(int theId) {
        return patientDao.findById(theId);
    }


}
