package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jira.dto.DepartmentDTO;
import com.jira.dto.UserDto;
import com.jira.entities.AuthRequest;
import com.jira.entities.AuthResponse;
import com.jira.entities.Users;
import com.jira.repository.UserRepository;
import com.jira.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	public UserDto registerUser(UserDto userDto) {
		
		userDto.setPassword(
		encoder.encode(userDto.getPassword()));
		
		Users user = modelMapper.map(userDto, Users.class);
		
		Users savedUser = userRepository.save(user);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public AuthResponse loginUser(AuthRequest authRequest) {
		
		return null;
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		
		List<Users> allUser = userRepository.findAll();
		
		List<UserDto> list = allUser.stream().map(user->modelMapper.map(user, UserDto.class)).toList();
		
		return list;
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		Users user = userRepository.findById(userId).orElse(null);
		
		if (user!=null) {
			userRepository.delete(user);
			flag = true;
		}
		
		return flag;
	}

	
	
}
