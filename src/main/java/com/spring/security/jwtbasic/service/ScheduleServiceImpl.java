package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.dao.ScheduleDao;
import com.spring.security.jwtbasic.dao.UserDao;
import com.spring.security.jwtbasic.entity.Role;
import com.spring.security.jwtbasic.entity.Schedule;
import com.spring.security.jwtbasic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void saveSchedule(Schedule schedule) {

        // save user in the database
        scheduleDao.saveSchedule(schedule);
    }
}
