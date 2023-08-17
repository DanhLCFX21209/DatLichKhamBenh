package com.spring.security.jwtbasic.service;

import com.spring.security.jwtbasic.entity.Status;

public interface StatusService {
    public Status findStatusByName(String name);

}
