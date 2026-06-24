package com.jira.dto;

import java.time.LocalDateTime;

import com.jira.entities.Task;
import com.jira.entities.Users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {

    private Integer commentId;

    private String content;

    private LocalDateTime createdAt;

    private Boolean edited;

    private Task task;

    private Users user;
	
}
