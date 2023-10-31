package com.startuptank.wbteam.service;

import com.startuptank.wbteam.entity.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProjectService {
    List<Project> findAll();
    List<Project> findByProjectFieldFieldName(String fieldName);

    Project findProjectByProjectId(Integer id);
}