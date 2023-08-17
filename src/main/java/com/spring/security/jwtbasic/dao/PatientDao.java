package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Patient;
import com.spring.security.jwtbasic.entity.User;

import java.util.List;

public interface PatientDao {
    public List<Patient> getPatients();

    public void savePatient(Patient patient);

    public Patient findById(int theId);



}
