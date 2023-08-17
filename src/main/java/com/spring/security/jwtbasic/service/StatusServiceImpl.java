package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.dao.StatusDao;
import com.spring.security.jwtbasic.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService{
    @Autowired
    private StatusDao statusDao;
    @Override
    public Status findStatusByName(String name) {
        return statusDao.findStatusByName(name);
    }
}
