package com.example.demo.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.demo.security.entity.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {
	@NotBlank
	private String fullName;
	@NotBlank
	private String username;
	@Email
	private String email;
	@NotBlank
	private String password;
	private Set<String> roles = new HashSet<>();
}
