package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.RoleDto;
import com.jira.dto.UserDto;
import com.jira.entities.Role;
import com.jira.entities.Users;
import com.jira.repository.RoleRepository;
import com.jira.repository.UserRepository;
import com.jira.service.RoleService;
@Service
public class RoleServiceImpl  implements RoleService{
	
	@Autowired
	ModelMapper mapper;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public RoleDto createRoles(RoleDto roleDto) {
		// TODO Auto-generated method stub
		Role role = mapper.map(roleDto, Role.class);
		
		Role savedRole = roleRepository.save(role);
		
		return mapper.map(savedRole, RoleDto.class);
	}

	@Override
	public List<RoleDto> getAllRoles() {
		// TODO Auto-generated method stub
		
		List<Role> allRole = roleRepository.findAll();
		
		List<RoleDto> roleDtos = allRole.stream().map(role->mapper.map(role, RoleDto.class)).toList();
		
		return roleDtos;
	}

	@Override
	public UserDto assignRoleToUser(Integer roleId, Integer userId) {
		// TODO Auto-generated method stub
		
		Role role = roleRepository.findById(roleId).orElse(null);
		
		Users users = userRepository.findById(userId).orElse(null);
		
		users.setRole(role);
		
		Users savedUsers = userRepository.save(users);
		
		return mapper.map(savedUsers, UserDto.class);
	}
	
	

}
