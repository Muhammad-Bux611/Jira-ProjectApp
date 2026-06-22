package com.jira.dto;

import java.time.LocalDateTime;

import com.jira.entities.Project;
import com.jira.entities.Users;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProjectMemberDTO {

	
    private Integer projectMemberId;

    private LocalDateTime joinedAt;

    private Boolean active;

    private ProjectDTO projectDTO;

    private UserDto userDto;
	
	
}
