package com.example.demo.security.service;

import java.util.Optional;

import com.example.demo.security.entity.UserEntity;

public interface UserService {

	void save(UserEntity userEntity);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	Optional<UserEntity> getUserByUsername(String username);

}
