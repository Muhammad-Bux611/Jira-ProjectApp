package com.jira.service;

import java.util.List;

import com.jira.dto.ProjectDTO;

public interface ProjectService {

	public ProjectDTO createProject(ProjectDTO projectDTO);
	public ProjectDTO getProjectById(Integer projectId);
	public List<ProjectDTO> getAllProject();
	public boolean deleteProjectById(Integer projectId);
	
}
