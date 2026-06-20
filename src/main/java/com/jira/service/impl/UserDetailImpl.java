package com.jira.service.impl;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jira.entities.Users;

public class UserDetailImpl implements UserDetails {
	
	Users users;
	
	public UserDetailImpl(Users users) {
		// TODO Auto-generated constructor stub
		
		this.users=users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_"+users.getRole().getRoleName()));
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getEmail();
	}

}
