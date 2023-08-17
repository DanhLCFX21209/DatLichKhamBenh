package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Schedule;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ScheduleDaoImpl implements ScheduleDao{
    private EntityManager entityManager;

    @Autowired
    public ScheduleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create the user ... finally LOL
        currentSession.saveOrUpdate(schedule);
    }
}
