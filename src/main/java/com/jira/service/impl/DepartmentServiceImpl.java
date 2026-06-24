package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.AuditLogDTO;
import com.jira.dto.DepartmentDTO;
import com.jira.entities.Department;
import com.jira.entities.Users;
import com.jira.exception.ResourceNotFoundException;
import com.jira.payloads.ActivityAction;
import com.jira.payloads.CurrentUserService;
import com.jira.payloads.EntityType;
import com.jira.repository.DepartmentRepo;
import com.jira.repository.UserRepository;
import com.jira.service.AuditLogService;
import com.jira.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final UserRepository userRepository;
	
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	AuditLogService auditLogService;
	@Autowired
	CurrentUserService currentUserService;

    DepartmentServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		
		Department department = mapper.map(departmentDTO, Department.class);
		
		Department savedDepartment = departmentRepo.save(department);
		
		AuditLogDTO auditLogDTO = new AuditLogDTO();
		auditLogDTO.setAction(ActivityAction.CREATE);
		auditLogDTO.setEntityType(EntityType.DEPARTMENT);
		auditLogDTO.setEntityId(department.getDeptId());
		auditLogDTO.setUserId(currentUserService.getCurrentUser().getUserId());
		auditLogDTO.setDescription("department "+department.getDeptName()+" is created");
		
		
		auditLogService.createLogs(auditLogDTO);
		
		
		
		return mapper.map(savedDepartment, DepartmentDTO.class);
	}

	@Override
	public DepartmentDTO getDepartmentById(Integer deptId) {
		// TODO Auto-generated method stub
		
		Department department = departmentRepo.findById(deptId).orElseThrow(()->new ResourceNotFoundException("Department not found with id :"+deptId));
		
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

		Department department = departmentRepo.findById(deptId).orElseThrow(()->new ResourceNotFoundException("Department not found with id :"+deptId));
		
		if (department!=null) {
			
			departmentRepo.delete(department);
			
			AuditLogDTO auditLogDTO = new AuditLogDTO();
			auditLogDTO.setAction(ActivityAction.DELETE);
			auditLogDTO.setEntityType(EntityType.DEPARTMENT);
			auditLogDTO.setEntityId(department.getDeptId());
			auditLogDTO.setUserId(currentUserService.getCurrentUser().getUserId());
			auditLogDTO.setDescription("department "+department.getDeptName()+" is deleted");
			
			
			auditLogService.createLogs(auditLogDTO);
			
			
			flag =true;
		}
		
		return flag;
	}

	@Override
	public DepartmentDTO assignDeptToUser(Integer deptId, Integer userId) {
		// TODO Auto-generated method stub

		Department department = departmentRepo.findById(deptId).orElseThrow(()->new ResourceNotFoundException("Department not found with id :"+deptId));
		Users users = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Department not found with id :"+deptId));
		
		if (department!=null && users!=null) {
			
			users.setDepartment(department);
			
			Users savedUsers = userRepository.save(users);
			
			AuditLogDTO auditLogDTO = new AuditLogDTO();
			auditLogDTO.setAction(ActivityAction.ASSIGN);
			auditLogDTO.setEntityType(EntityType.DEPARTMENT);
			auditLogDTO.setEntityId(department.getDeptId());
			auditLogDTO.setUserId(currentUserService.getCurrentUser().getUserId());
			auditLogDTO.setDescription("department "+department.getDeptName()+" is assigned to user");
			
			
			auditLogService.createLogs(auditLogDTO);
			
		}
		
		
		return mapper.map(department, DepartmentDTO.class);
	}

}
