package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Clinic;
import com.spring.security.jwtbasic.entity.Specialization;
import com.spring.security.jwtbasic.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ClinicDaoImpl implements ClinicDao{
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public ClinicDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Clinic> getClinics() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Clinic> theQuery =
                currentSession.createQuery("from Clinic order by view DESC", Clinic.class);

        // execute query and get result list
        List<Clinic> clinics = theQuery.getResultList();

        // return the results
        return clinics;
    }

    @Override
    public Clinic findUserByName(String theName) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using username
        Query<Clinic> theQuery = currentSession.createQuery("from Clinic where name=:name", Clinic.class);
        theQuery.setParameter("name", theName);
        Clinic theClinic = null;
        try {
            theClinic = theQuery.getSingleResult();
        } catch (Exception e) {
            theClinic = null;
        }

        return theClinic;
    }
}
