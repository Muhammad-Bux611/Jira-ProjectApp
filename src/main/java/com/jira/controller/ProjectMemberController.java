package com.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
