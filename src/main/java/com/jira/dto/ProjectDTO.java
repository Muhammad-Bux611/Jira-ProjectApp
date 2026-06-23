package com.jira.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.jira.payloads.ProjectStatus;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

public class ProjectDTO {

	
	private Integer projectId;

    private String projectName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal budget;

    private Boolean active;

    
    private ProjectStatus status;

    private DepartmentDTO department;
	
	
}
