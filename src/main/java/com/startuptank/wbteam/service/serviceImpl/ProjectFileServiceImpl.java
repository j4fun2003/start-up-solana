package com.startuptank.wbteam.service.serviceImpl;

import com.startuptank.wbteam.entity.ProjectField;
import com.startuptank.wbteam.repository.ProjectFieldRepo;
import com.startuptank.wbteam.service.ProjectFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectFileServiceImpl implements ProjectFieldService {
    private final ProjectFieldRepo projectFieldRepo;
    @Autowired
    public ProjectFileServiceImpl(ProjectFieldRepo projectFieldRepo) {
        this.projectFieldRepo = projectFieldRepo;
    }

    @Override
    public List<ProjectField> findAll() {
        return projectFieldRepo.findAll();
    }
}
