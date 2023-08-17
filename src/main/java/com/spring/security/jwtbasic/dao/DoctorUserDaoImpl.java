package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.DoctorUser;
import com.spring.security.jwtbasic.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DoctorUserDaoImpl implements DoctorUserDao{
    private EntityManager entityManager;

    @Autowired
    public DoctorUserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DoctorUser> findByDoctorUserName(String name) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        String hqlQuery = "SELECT d FROM DoctorUser d " +
                "JOIN d.specialization s " +
                "WHERE s.name LIKE :specializationName";

        // now retrieve/read from database using username
        Query<DoctorUser> theQuery = currentSession.createQuery(hqlQuery, DoctorUser.class);
        theQuery.setParameter("specializationName","%" + name + "%");

        List<DoctorUser> doctorUsers = theQuery.getResultList();

        return doctorUsers;
    }

    @Override
    public List<DoctorUser> findAll(String keyword) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        String hql = "SELECT du FROM DoctorUser du " +
                "JOIN du.specialization s " +
                "JOIN du.place p " +
                "JOIN du.clinic c " +
                "WHERE s.name like :specializationName " +
                "OR p.name like :placeName " +
                "OR c.price = :priceName " +
                "OR c.name like :clinicName";

        // now retrieve/read from database using username
        Query<DoctorUser> theQuery = currentSession.createQuery(hql, DoctorUser.class);
        theQuery.setParameter("clinicName","%" + keyword + "%");
        theQuery.setParameter("specializationName","%" + keyword + "%");
        theQuery.setParameter("placeName","%" + keyword + "%");
        theQuery.setParameter("priceName",keyword);
        List<DoctorUser> doctorUsers = theQuery.getResultList();

        return doctorUsers;
    }

    @Override
    public void saveDoctor(DoctorUser doctorUser) {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create the user ... finally LOL
        currentSession.saveOrUpdate(doctorUser);
    }
}
