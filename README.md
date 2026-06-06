# Jira-ProjectApp
## Overview

#Jira-ProjectApp is a backend application built using Spring Boot following enterprise-level architecture principles. The system is designed to manage users, departments, projects, teams, tasks, comments, and activity logs within an organization.

The application simulates how modern software companies manage projects, teams, and workflows. It provides secure authentication, role-based authorization, project planning, task assignment, team collaboration, and activity tracking.

This project focuses entirely on backend development and demonstrates practical implementation of Spring Boot, Spring Security, Hibernate, JPA relationships, REST APIs, validation, exception handling, logging, and clean architecture.

---

## Objectives

* Learn enterprise backend architecture
* Understand Spring Security internals
* Practice Hibernate and JPA relationships
* Implement Role-Based Access Control (RBAC)
* Develop RESTful APIs
* Apply logging and debugging strategies
* Build production-style service and repository layers
* Understand software-house level project structure

---

## Technology Stack

### Backend

* Java 17+
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* Lombok
* ModelMapper

### Tools

* Maven
* Postman
* Git
* GitHub

---

## System Architecture

```text
Client Request
      |
      v
Spring Security Filter Chain
      |
      v
Controller Layer
      |
      v
Service Layer
      |
      v
Repository Layer
      |
      v
Hibernate / JPA
      |
      v
MySQL Database
      |
      v
Response
```

---

## Modules

### 1. Authentication & Authorization Module

Responsible for authentication and access control.

#### Features

* User Registration
* User Login
* User Logout
* Password Encryption (BCrypt)
* Session-Based Authentication
* Role-Based Authorization

#### Roles

* ADMIN
* PROJECT_MANAGER
* DEVELOPER
* TESTER

---

### 2. Department Management Module

Responsible for managing organizational departments.

#### Features

* Create Department
* Update Department
* Delete Department
* View Departments
* Assign Users to Departments

#### Relationship

```text
Department (1) ------ (*) Users
```

One department can contain multiple users.

---

### 3. Project Management Module

Responsible for project lifecycle management.

#### Features

* Create Project
* Update Project
* Delete Project
* View Projects
* Manage Project Status

#### Project Status

* PLANNING
* IN_PROGRESS
* COMPLETED
* ON_HOLD

---

### 4. Team Management Module

Responsible for assigning users to projects.

#### Features

* Add Members to Project
* Remove Members from Project
* View Project Team

#### Relationship

```text
User (*) <------> (*) Project

Implemented using ProjectMember entity
```

---

### 5. Task Management Module

Core business module of the system.

#### Features

* Create Task
* Assign Task
* Update Task Status
* Set Task Priority
* Set Due Date
* Track Progress

#### Task Status

* TODO
* IN_PROGRESS
* TESTING
* DONE

#### Priority Levels

* LOW
* MEDIUM
* HIGH
* CRITICAL

---

### 6. Comment Management Module

Provides communication and collaboration between team members.

#### Features

* Add Comment
* View Comments
* Task Discussions

#### Relationships

```text
Task (1) ------ (*) Comments

User (1) ------ (*) Comments
```

---

### 7. Activity Log Module

Tracks all important actions performed within the system.

#### Features

* Store Activity History
* User Action Tracking
* System Auditing
* Debugging Support

#### Examples

* Project Created
* Task Assigned
* User Added
* Task Updated
* Task Deleted

---

## Database Design

### Core Entities

* User
* Role
* Department
* Project
* ProjectMember
* Task
* Comment
* ActivityLog

---

## Entity Relationships

```text
Role (1) ------ (*) User

Department (1) ------ (*) User

Project (1) ------ (*) Task

User (1) ------ (*) Task

Task (1) ------ (*) Comment

User (1) ------ (*) Comment

User (1) ------ (*) ActivityLog

User (*) <------> (*) Project
            |
            |
     ProjectMember
```

---

## Security Implementation

Spring Security is implemented using Session-Based Authentication.

### Features

* User Authentication
* Password Encryption using BCrypt
* Endpoint Authorization
* Role-Based Access Control (RBAC)
* Secure API Access

---

## Logging Strategy

The application uses structured logging for monitoring and debugging.

### Log Levels

* INFO
* DEBUG
* WARN
* ERROR

### Logged Activities

* User Login
* User Registration
* Project Creation
* Project Updates
* Task Assignment
* Task Status Changes
* Validation Failures
* Exception Tracking

---

## Exception Handling

Global exception handling is implemented using custom exceptions.

### Examples

* UserNotFoundException
* DepartmentNotFoundException
* ProjectNotFoundException
* TaskNotFoundException
* DuplicateResourceException

---

## API Testing

All APIs are tested using Postman.

### Testing Coverage

* Success Cases
* Validation Cases
* Authentication Cases
* Authorization Cases
* Error Cases
* Edge Cases

---

## Learning Outcomes

This project demonstrates practical understanding of:

* Spring Boot Fundamentals
* Spring MVC
* Spring Security
* Hibernate ORM
* JPA Relationships
* REST API Development
* DTO Pattern
* Bean Validation
* Exception Handling
* Logging & Debugging
* Enterprise Application Architecture
* Software Development Best Practices

```
```
