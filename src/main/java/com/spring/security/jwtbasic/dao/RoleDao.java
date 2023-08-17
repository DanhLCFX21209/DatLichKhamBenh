package com.spring.security.jwtbasic.dao;

import com.spring.security.jwtbasic.entity.Role;

public interface RoleDao {
	public Role findRoleByName(String theRoleName);
	
}
