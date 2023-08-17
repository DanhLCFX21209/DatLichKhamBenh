package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.dao.ClinicDao;
import com.spring.security.jwtbasic.dao.PlaceDao;
import com.spring.security.jwtbasic.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PlaceServiceImpl implements PlaceService{
    @Autowired
    private PlaceDao placeDao;

    @Override
    @Transactional
    public Place findUserByName(String theName) {
        return placeDao.findUserByName(theName);
    }
}
