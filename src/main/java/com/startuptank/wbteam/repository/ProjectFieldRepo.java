package com.startuptank.wbteam.repository;

import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.entity.ProjectField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectFieldRepo extends JpaRepository<ProjectField,Integer> {


}
