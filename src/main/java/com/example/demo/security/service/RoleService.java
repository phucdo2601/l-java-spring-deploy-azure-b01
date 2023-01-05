package com.example.demo.security.service;

import java.util.Optional;

import com.example.demo.security.entity.RoleEntity;
import com.example.demo.security.enums.RoleEnum;

public interface RoleService {

	Optional<RoleEntity> getByRoleName(RoleEnum roleName);

	void saveRole(RoleEntity roleEntity);

}
