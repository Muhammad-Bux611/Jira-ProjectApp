package com.jira.service;

import java.util.List;

import com.jira.dto.AuditLogDTO;
import com.jira.payloads.ActivityAction;
import com.jira.payloads.EntityType;

public interface AuditLogService {

	public  AuditLogDTO createLogs(AuditLogDTO auditLogDTO);
	public List<AuditLogDTO> getAllLogs();
	public AuditLogDTO getLogsById(Integer logId);
	public List<AuditLogDTO> getLogByUser(Integer userId);
	public List<AuditLogDTO> getLogsByAction(ActivityAction action);
	public List<AuditLogDTO> getLogsByEntity(EntityType type);
	
	
}
