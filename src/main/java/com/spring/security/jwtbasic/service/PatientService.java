package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PatientService {
    public List<Patient> getPatients();

    public void savePatient(Patient patient);

    public Patient findById(int theId);

}
