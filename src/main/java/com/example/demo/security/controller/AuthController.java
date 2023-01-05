package com.example.demo.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MessageNoti;
import com.example.demo.security.dto.JwtDto;
import com.example.demo.security.dto.LoginUser;
import com.example.demo.security.dto.NewUserDto;
import com.example.demo.security.entity.RoleEntity;
import com.example.demo.security.entity.UserEntity;
import com.example.demo.security.enums.RoleEnum;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.service.RoleService;
import com.example.demo.security.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping("/createUser")
	public ResponseEntity<?> registerAccount(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new MessageNoti("The email is not valid!"), HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByUsername(newUserDto.getUsername())) {
			return new ResponseEntity(new MessageNoti("The username is existed in the system!"), HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByEmail(newUserDto.getEmail())) {
			return new ResponseEntity(new MessageNoti("The email is existed in the system!"), HttpStatus.BAD_REQUEST);
		}
		
		UserEntity userEntity = new UserEntity(
				newUserDto.getFullName(),
				newUserDto.getUsername(),
				newUserDto.getEmail(),
				passwordEncoder.encode(newUserDto.getPassword())
				);
		
		Set<RoleEntity> roles = new HashSet<>();
		roles.add(roleService.getByRoleName(RoleEnum.ROLE_USER).get());
		
		if (newUserDto.getRoles().contains("admin")) {
			roles.add(roleService.getByRoleName(RoleEnum.ROLE_ADMIN).get());
		}
		
		userEntity.setRoles(roles);
		userService.save(userEntity);
        return new ResponseEntity(new MessageNoti("Create Successfully!"), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login (@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new MessageNoti("The data login is not valid!"), HttpStatus.BAD_REQUEST);
		}
		Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generationToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto = new JwtDto(
        		jwt,
        		userDetails.getUsername(),
        		userDetails.getAuthorities()
        		);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
	
	
}
