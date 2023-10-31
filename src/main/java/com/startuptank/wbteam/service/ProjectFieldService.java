package com.startuptank.wbteam.service;

import com.startuptank.wbteam.entity.ProjectField;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProjectFieldService {
    List<ProjectField> findAll();
}
