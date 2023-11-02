package com.startuptank.wbteam.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.startuptank.wbteam.entity.ProjectField;

@Component
public interface ProjectFieldService {

    List<ProjectField> findAll();

    ProjectField findById(int jobType);
    
}
