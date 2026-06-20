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

import com.jira.dto.ProjectDTO;
import com.jira.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	ProjectService projectService ;
	
	@PostMapping("/")
	public ProjectDTO createProjectUsingDto(@RequestBody ProjectDTO projectDTO) {
		
	return	projectService.createProject(projectDTO);
		
	}
	
	@GetMapping("/{projectId}")
	public ProjectDTO projectById(@PathVariable("projectId")Integer projectId) {
		
		return projectService.getProjectById(projectId);
	}
	
	@GetMapping("/")
	public List<ProjectDTO> allProjects(){
		return projectService.getAllProject();
	}
	
	@DeleteMapping("/{projectId}")
	public String deleteProjectById(@PathVariable("projectId")Integer projectId) {
		
		boolean deleteProjectById = projectService.deleteProjectById(projectId);
		if (deleteProjectById) {
			return "Project is successfully deleted";
		}
		else {
			return "Project is not present in the database";
		}
		
	}
	
}
