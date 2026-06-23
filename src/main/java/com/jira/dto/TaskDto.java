package com.jira.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jira.payloads.TaskPriority;
import com.jira.payloads.TaskStatus;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

public class TaskDto {

	

    private Integer taskId;

    private String title;

    private String description;

    private TaskPriority priority;

    private TaskStatus status;

    private LocalDate startDate;

    private LocalDate dueDate;

    private LocalDateTime createdAt;

    private Boolean active;

    
    private ProjectDTO project;

    private UserDto assignedUser;
	
}
