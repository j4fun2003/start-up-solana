package com.startuptank.wbteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startuptank.wbteam.entity.ProjectField;

public interface ProjectFieldDAO extends JpaRepository<ProjectField,Integer> {
    
}
