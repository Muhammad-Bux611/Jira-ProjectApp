package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.DepartmentDTO;
import com.jira.entities.Department;
import com.jira.entities.Users;
import com.jira.repository.DepartmentRepo;
import com.jira.repository.UserRepository;
import com.jira.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final UserRepository userRepository;
	
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	ModelMapper mapper;

    DepartmentServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		
		Department department = mapper.map(departmentDTO, Department.class);
		
		Department savedDepartment = departmentRepo.save(department);
		
		
		return mapper.map(savedDepartment, DepartmentDTO.class);
	}

	@Override
	public DepartmentDTO getDepartmentById(Integer deptId) {
		// TODO Auto-generated method stub
		
		Department department = departmentRepo.findById(deptId).orElse(null);
		
		return mapper.map(department, DepartmentDTO.class);
	}

	@Override
	public List<DepartmentDTO> getAllDepartment() {
		// TODO Auto-generated method stub
		
		List<Department> departments = departmentRepo.findAll();
		
		List<DepartmentDTO> departmentDTOs = departments.stream().map(dept->mapper.map(dept, DepartmentDTO.class)).toList();
		
		return departmentDTOs;
	}

	@Override
	public DepartmentDTO updateDepartmentDTO(DepartmentDTO updateDepartmentDTO) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	@Override
	public boolean deleteDepartmentById(Integer deptId) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		Department department = departmentRepo.findById(deptId).orElse(null);
		
		if (department!=null) {
			
			departmentRepo.delete(department);;
			flag =true;
		}
		
		return flag;
	}

	@Override
	public DepartmentDTO assignDeptToUser(Integer deptId, Integer userId) {
		// TODO Auto-generated method stub
		
		Department department = departmentRepo.findById(deptId).orElse(null);
		Users users = userRepository.findById(userId).orElse(null);
		
		if (department!=null && users!=null) {
			
			users.setDepartment(department);
			
			Users savedUsers = userRepository.save(users);
		}
		
		
		return mapper.map(department, DepartmentDTO.class);
	}

}
