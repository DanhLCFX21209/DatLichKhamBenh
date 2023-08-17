package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.entity.Place;

public interface PlaceService {
    public Place findUserByName(String theName);

}
