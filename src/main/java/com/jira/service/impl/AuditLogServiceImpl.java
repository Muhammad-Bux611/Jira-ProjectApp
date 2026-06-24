package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.AuditLogDTO;
import com.jira.entities.AuditLog;
import com.jira.exception.ResourceNotFoundException;
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
		
		AuditLog log = auditLogRepository.findById(logId).orElseThrow(()->new ResourceNotFoundException("Log with id :"+logId+" not found"));
		
		return mapper.map(log, AuditLogDTO.class);
	}

	@Override
	public List<AuditLogDTO> getLogByUser(Integer userId) {

		List<AuditLog> logs = auditLogRepository.findByUserUserId(userId);
		
		if (logs.isEmpty()) {
			throw new ResourceNotFoundException("Not any log is performed in this project so you have to perform some activity");
		}
		List<AuditLogDTO> listOfLogs = logs.stream().map(log->mapper.map(log, AuditLogDTO.class)).toList();
		return listOfLogs;
	}

	@Override
	public List<AuditLogDTO> getLogsByAction(ActivityAction action) {
		List<AuditLog> logs = auditLogRepository.findByAction(action);
		
		if (logs.isEmpty()) {
			throw new ResourceNotFoundException("Not any log is performed with respect to this perticular action "+action.name());
		}

		List<AuditLogDTO> listOfLogs = logs.stream().map(log->mapper.map(log, AuditLogDTO.class)).toList();
		return listOfLogs;
		
	}

	@Override
	public List<AuditLogDTO> getLogsByEntity(EntityType type) {
		
		List<AuditLog> logs = auditLogRepository.findByEntityType(type);
		
		if (logs.isEmpty()) {
			throw new ResourceNotFoundException("Not any log is performed with respect to this perticular entity "+type.name());
		}

		List<AuditLogDTO> listOfLogs = logs.stream().map(log->mapper.map(log, AuditLogDTO.class)).toList();
		return listOfLogs;
		
	
	}

}
