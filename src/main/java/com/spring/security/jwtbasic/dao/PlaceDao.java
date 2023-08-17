package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Place;

public interface PlaceDao {
    public Place findUserByName(String theName);

}
