package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.security.entity.RoleEntity;
import com.example.demo.security.enums.RoleEnum;
import com.example.demo.security.service.RoleService;

@Component
public class CreateRoles implements CommandLineRunner {
	
	@Autowired
	private RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		RoleEntity roleAdmin =  new RoleEntity(RoleEnum.ROLE_ADMIN);
//		RoleEntity roleUser =  new RoleEntity(RoleEnum.ROLE_USER);
//		roleService.saveRole(roleUser);
//		roleService.saveRole(roleAdmin);
	}
	
}
