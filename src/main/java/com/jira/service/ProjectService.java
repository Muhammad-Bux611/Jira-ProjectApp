package com.jira.service;

import java.util.List;

import com.jira.dto.ProjectDTO;
import com.jira.payloads.ProjectStatus;

public interface ProjectService {

	public ProjectDTO createProject(ProjectDTO projectDTO);
	public ProjectDTO getProjectById(Integer projectId);
	public List<ProjectDTO> getAllProject();
	public boolean deleteProjectById(Integer projectId);
	public  List<ProjectDTO> getProjectByStatus(ProjectStatus projectStatus);
	public ProjectDTO assignProjectToDepartment(Integer projectId, Integer deptId);
	
}
