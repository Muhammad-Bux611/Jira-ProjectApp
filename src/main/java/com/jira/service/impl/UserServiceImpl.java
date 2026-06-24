package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.jira.dto.AuditLogDTO;
import com.jira.dto.DepartmentDTO;
import com.jira.dto.UserDto;
import com.jira.entities.AuditLog;
import com.jira.entities.AuthRequest;
import com.jira.entities.AuthResponse;
import com.jira.entities.Users;
import com.jira.exception.ResourceNotFoundException;
import com.jira.payloads.ActivityAction;
import com.jira.payloads.CurrentUserService;
import com.jira.payloads.EntityType;
import com.jira.repository.UserRepository;
import com.jira.service.AuditLogService;
import com.jira.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	CurrentUserService currentUserService;
	@Autowired
	AuditLogService auditLogService;

	@Override
	public UserDto registerUser(UserDto userDto) {
		
		userDto.setPassword(
		encoder.encode(userDto.getPassword()));
		
		Users user = modelMapper.map(userDto, Users.class);
		
		Users savedUser = userRepository.save(user);
		
		AuditLogDTO auditLogDTO = new AuditLogDTO();
		auditLogDTO.setAction(ActivityAction.CREATE);
		auditLogDTO.setEntityType(EntityType.USER);
		auditLogDTO.setEntityId(userDto.getUserId());
		auditLogDTO.setUserId(userDto.getUserId());
		auditLogDTO.setDescription("user "+userDto.getFirstName()+" is created");
		
		
		auditLogService.createLogs(auditLogDTO);
		
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
		
		Users user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with id :"+userId));
		
		if (user!=null) {
			userRepository.delete(user);
			
			AuditLogDTO auditLogDTO = new AuditLogDTO();
			auditLogDTO.setAction(ActivityAction.DELETE);
			auditLogDTO.setEntityType(EntityType.USER);
			auditLogDTO.setEntityId(user.getUserId());
			auditLogDTO.setUserId(user.getUserId());
			auditLogDTO.setDescription("user "+user.getFirstName()+" is deleted");
			
			
			auditLogService.createLogs(auditLogDTO);
			
			
			flag = true;
		}
		
		return flag;
	}

	
	
}
