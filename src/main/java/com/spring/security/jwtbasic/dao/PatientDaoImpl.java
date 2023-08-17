package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Patient;
import com.spring.security.jwtbasic.entity.Specialization;
import com.spring.security.jwtbasic.entity.Status;
import com.spring.security.jwtbasic.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PatientDaoImpl implements PatientDao{
    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public PatientDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Patient> getPatients() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Patient> theQuery =
                currentSession.createQuery("from Patient", Patient.class);

        // execute query and get result list
        List<Patient> patients = theQuery.getResultList();

        // return the results
        return patients;
    }

    @Override
    public void savePatient(Patient patient) {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create the user ... finally LOL
        currentSession.saveOrUpdate(patient);
    }

    @Override
    public Patient findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        Patient thePatient =
                currentSession.get(Patient.class, theId);

        // return the employee
        return thePatient;
    }

}
