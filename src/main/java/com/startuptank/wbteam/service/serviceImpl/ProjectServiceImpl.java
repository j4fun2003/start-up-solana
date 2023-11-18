package com.startuptank.wbteam.service.serviceImpl;

import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.repository.ProjectRepo;
import com.startuptank.wbteam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;
    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo){
        this.projectRepo = projectRepo;
    }
    @Override
    public List<Project> findAll() {
        return projectRepo.findAll();
    }

    @Override
    public List<Project> findByProjectFieldFieldName(String fieldName) {
        return projectRepo.findByProjectFieldFieldName(fieldName);
    }

    @Override
    public Project findProjectByProjectId(Integer id) {
        return projectRepo.findProjectByProjectId(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepo.save(project);
    }
    public List<Project> findAllbyUser(long id) {
        return projectRepo.findAllByUser(id);
    }
}
