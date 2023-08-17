package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Patient;
import com.spring.security.jwtbasic.entity.Role;
import com.spring.security.jwtbasic.entity.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class StatusDaoImpl implements StatusDao{
    private EntityManager entityManager;

    @Autowired
    public StatusDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Status findStatusByName(String name) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<Status> theQuery = currentSession.createQuery("from Status where name=:name", Status.class);
        theQuery.setParameter("name", name);

        Status theStatus = null;

        try {
            theStatus = theQuery.getSingleResult();
        } catch (Exception e) {
            theStatus = null;
        }

        return theStatus;
    }

}
