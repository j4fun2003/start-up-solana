package com.startuptank.wbteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startuptank.wbteam.entity.Project;

public interface ProjectDAO extends JpaRepository<Project,Integer>  {
    
}
