package com.example.demo.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {
	private String token;
	private String bearer = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> auhAuthorities;

	public JwtDto(String token, String username, Collection<? extends GrantedAuthority> auhAuthorities) {
		this.token = token;
		this.username = username;
		this.auhAuthorities = auhAuthorities;
	}

}
