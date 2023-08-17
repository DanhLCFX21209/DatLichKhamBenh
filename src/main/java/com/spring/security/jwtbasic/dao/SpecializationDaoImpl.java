package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Specialization;
import com.spring.security.jwtbasic.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SpecializationDaoImpl implements SpecializationDao{


    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public SpecializationDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Specialization> getSpecializations() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Specialization> theQuery =
                currentSession.createQuery("from Specialization order by view DESC", Specialization.class);

        // execute query and get result list
        List<Specialization> specializations = theQuery.getResultList();

        // return the results
        return specializations;
    }

    @Override
    public Specialization findSpecializationByName(String theName) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using username
        Query<Specialization> theQuery = currentSession.createQuery("from Specialization where name=:name", Specialization.class);
        theQuery.setParameter("name", theName);
        Specialization theSpecialization = null;
        try {
            theSpecialization = theQuery.getSingleResult();
        } catch (Exception e) {
            theSpecialization = null;
        }

        return theSpecialization;
    }
}
