package com.jira.dto;

import com.jira.payloads.ActivityAction;
import com.jira.payloads.EntityType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuditLogDTO {


	    private Integer userId;

	    private ActivityAction action;

	    private EntityType entityType;

	    private Long entityId;

	    private String description;
	
	
	
}
