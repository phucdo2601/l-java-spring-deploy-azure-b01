package com.example.demo.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.security.entity.UserEntity;
import com.example.demo.security.repository.UserRepository;
import com.example.demo.security.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<UserEntity> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
		
	}
	
	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	@Override
	public void save(UserEntity userEntity) {
		userRepository.save(userEntity);
	}
}
