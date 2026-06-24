package com.jira.entities;

import java.time.LocalDateTime;

import com.jira.payloads.ActivityAction;
import com.jira.payloads.EntityType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AuditLog {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer logId;


	    @Enumerated(EnumType.STRING)
	    private ActivityAction action;

	    @Enumerated(EnumType.STRING)
	    private EntityType entityType;

	    private Integer entityId;

	    private String description;

	    private LocalDateTime createdAt;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private Users user;
	

}
