package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
