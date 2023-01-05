package com.example.demo.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.security.entity.RoleEntity;
import com.example.demo.security.enums.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{

	Optional<RoleEntity> findByRoleName(RoleEnum roleName);
	
}
