package com.jira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.dto.ProjectMemberDTO;
import com.jira.service.ProjectMemberService;

@RestController
@RequestMapping("/api")
public class ProjectMemberController {

	@Autowired
	private ProjectMemberService projectMemberService;
	
	@PostMapping("/create/projects/{projectId}/users/{userId}/member")
	public ResponseEntity<?> createProjectmember(@PathVariable("projectId")Integer projectId,@PathVariable("userId") Integer userId) {
		
		boolean checkdublicateAssignment = projectMemberService.checkdublicateAssignment(projectId, userId);
		if (checkdublicateAssignment) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Sorry!! You don't have to store the dublicate data");
		}
		
		ProjectMemberDTO projectMember = projectMemberService.createProjectMember(projectId,userId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(projectMember);
	}
	
	
	@GetMapping("/projects/{projectId}/members")
	public ResponseEntity<?> getAllUsersForSpecificProject(@PathVariable Integer projectId){
		
		List<ProjectMemberDTO> listOfProjectMemberByProjectId = projectMemberService.listOfProjectMemberByProjectId(projectId);

		return ResponseEntity.status(HttpStatus.FOUND).body(listOfProjectMemberByProjectId);
		
	}
	
	
	@DeleteMapping("/projects/{projectId}/member/{userId}")
	public ResponseEntity<?> deleteUserFromProject(@PathVariable Integer projectId,@PathVariable Integer userId){
		
		boolean isRemoved = projectMemberService.removeUserFromProject(projectId, userId);
		if (!isRemoved) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project is not found with the specific user");
			
			
		}else {
		
			return ResponseEntity.status(HttpStatus.FOUND).body("successfully removed user from the specific user");
			
			
		}
		
	}
}
