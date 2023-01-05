package com.example.demo.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.security.entity.RoleEntity;
import com.example.demo.security.enums.RoleEnum;
import com.example.demo.security.repository.RoleRepository;
import com.example.demo.security.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<RoleEntity> getByRoleName(RoleEnum roleName) {
		return roleRepository.findByRoleName(roleName);
	}
	
	@Override
	public void saveRole(RoleEntity roleEntity) {
		roleRepository.save(roleEntity);
	}
}
