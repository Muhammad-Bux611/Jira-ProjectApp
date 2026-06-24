package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.AuditLogDTO;
import com.jira.entities.AuditLog;
import com.jira.payloads.ActivityAction;
import com.jira.payloads.EntityType;
import com.jira.repository.AuditLogRepository;
import com.jira.service.AuditLogService;
@Service
public class AuditLogServiceImpl  implements AuditLogService{

	@Autowired
	private AuditLogRepository auditLogRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public AuditLogDTO createLogs(AuditLogDTO auditLogDTO) {

		AuditLog log = mapper.map(auditLogDTO, AuditLog.class);
		
		AuditLog savedLog = auditLogRepository.save(log);
		
		return mapper.map(savedLog, AuditLogDTO.class);
	}

	@Override
	public List<AuditLogDTO> getAllLogs() {

		List<AuditLog> listOfAuditLog = auditLogRepository.findAll();
		List<AuditLogDTO> logs = listOfAuditLog.stream().map(log->mapper.map(log, AuditLogDTO.class)).toList();
		
		return logs;
	}

	@Override
	public AuditLogDTO getLogsById(Integer logId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuditLogDTO> getLogByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuditLogDTO> getLogsByAction(ActivityAction action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuditLogDTO> getLogsByEntity(EntityType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
