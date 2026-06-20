package com.jira.service;

import java.util.List;

import com.jira.dto.DepartmentDTO;

public interface DepartmentService {

	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
	public DepartmentDTO getDepartmentById(Integer deptId);
	public List<DepartmentDTO> getAllDepartment();
	public DepartmentDTO updateDepartmentDTO(DepartmentDTO updateDepartmentDTO);
	public boolean deleteDepartmentById(Integer deptId);
	public DepartmentDTO assignDeptToUser(Integer deptId, Integer userId);
}
