package com.jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.entities.AuditLog;
import com.jira.payloads.ActivityAction;
import com.jira.payloads.EntityType;

public interface AuditLogRepository  extends JpaRepository<AuditLog, Integer>{
	
	List<AuditLog> findByUserUserId(Integer userId);

	List<AuditLog> findByEntityType(EntityType entityType);

	List<AuditLog> findByAction(ActivityAction action);

}
