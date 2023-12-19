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
    Project save(Project project);
<<<<<<< HEAD

    List<Project> findAllbyUser(long id);
=======
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd
}
