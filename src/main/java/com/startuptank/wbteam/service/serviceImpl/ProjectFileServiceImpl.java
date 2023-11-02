package com.startuptank.wbteam.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startuptank.wbteam.entity.ProjectField;
import com.startuptank.wbteam.repository.ProjectFieldDAO;
import com.startuptank.wbteam.service.ProjectFieldService;

@Service
public class ProjectFileServiceImpl implements ProjectFieldService {

    @Autowired
    ProjectFieldDAO pFieldDAO;
    @Override
    public List<ProjectField> findAll() {
        // TODO Auto-generated method stub
        return pFieldDAO.findAll();

    }
    @Override
    public ProjectField findById(int jobType) {
        // TODO Auto-generated method stub
        return pFieldDAO.findById(jobType).get();
    }
    
}
