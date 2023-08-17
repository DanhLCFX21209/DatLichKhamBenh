package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Patient;
import com.spring.security.jwtbasic.entity.Status;
import org.springframework.stereotype.Repository;

public interface StatusDao {
    public Status findStatusByName(String name);

}
