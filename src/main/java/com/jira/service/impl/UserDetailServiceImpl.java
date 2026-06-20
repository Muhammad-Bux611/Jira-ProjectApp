package com.jira.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jira.entities.Users;
import com.jira.repository.UserRepository;
@Component
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// TODO Auto-generated method stub
		
		Users users = userRepository.findByEmail(username).orElse(null);
		
		System.out.println(users);
		
		return new UserDetailImpl(users);
	}

}
