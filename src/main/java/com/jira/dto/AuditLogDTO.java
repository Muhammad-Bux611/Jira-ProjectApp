package com.jira.dto;

import com.jira.payloads.ActivityAction;
import com.jira.payloads.EntityType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuditLogDTO {


	    private Integer userId;


	    @Enumerated(EnumType.STRING)
	    private ActivityAction action;


	    @Enumerated(EnumType.STRING)
	    private EntityType entityType;

	    private Integer entityId;

	    private String description;
	
	
	
}
