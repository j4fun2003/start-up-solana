package com.startuptank.wbteam.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.repository.ProjectDAO;
import com.startuptank.wbteam.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDAO pDao;
    @Override
    public void update(Project p) {
        // TODO Auto-generated method stub
        pDao.save(p);
    }
    
}
