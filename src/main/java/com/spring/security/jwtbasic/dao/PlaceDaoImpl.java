package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Clinic;
import com.spring.security.jwtbasic.entity.Place;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PlaceDaoImpl implements PlaceDao{
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public PlaceDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public Place findUserByName(String theName) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using username
        Query<Place> theQuery = currentSession.createQuery("from Place where name=:name", Place.class);
        theQuery.setParameter("name", theName);
        Place thePlace = null;
        try {
            thePlace = theQuery.getSingleResult();
        } catch (Exception e) {
            thePlace = null;
        }

        return thePlace;
    }
}
