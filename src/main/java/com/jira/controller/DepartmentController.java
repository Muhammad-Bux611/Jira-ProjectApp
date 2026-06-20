package com.jira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.dto.DepartmentDTO;
import com.jira.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	
	@Autowired
	DepartmentService departmentService;
	
	@PostMapping("/")
	public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO) {
		
		DepartmentDTO dto = departmentService.createDepartment(departmentDTO);
		
		return dto;
		
	}
	
	@GetMapping("/")
	public List<DepartmentDTO> getDepartmentDTOs(){
		return departmentService.getAllDepartment();
	}
	
	@GetMapping("/{deptId}")

	public DepartmentDTO getDepartmentDTO(@PathVariable("deptId") Integer deptId) {
		DepartmentDTO dto = departmentService.getDepartmentById(deptId);
		
		return dto;
	}
	
	
	
	@DeleteMapping("/{deptId}")

	public String departmentDTO(@PathVariable("deptId") Integer deptId) {
		boolean deleteDepartmentById = departmentService.deleteDepartmentById(deptId);
		
		if (deleteDepartmentById) {
			return "department is deleted successfully";
		}else {
		
			return "department is not exist in the database";
			
		}
		
	}
	
	@PostMapping("/assignDepartment/department/{deptId}/users/{userId}")
	
	public DepartmentDTO assignDepartment(@PathVariable("deptId")Integer deptId,@PathVariable("userId") Integer userId) {
		
		DepartmentDTO assignDeptToUser = departmentService.assignDeptToUser(deptId, userId);
		
		return assignDeptToUser;
	}
	
}
