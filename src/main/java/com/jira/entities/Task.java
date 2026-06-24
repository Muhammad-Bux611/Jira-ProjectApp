package com.jira.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.jira.payloads.TaskPriority;
import com.jira.payloads.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDate startDate;

    private LocalDate dueDate;

    private LocalDateTime createdAt;

    private Boolean active;

    @ManyToOne
    
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private Users assignedUser;
    
    @OneToMany(mappedBy = "task")
    private List<Comment> comment;
}