package com.startuptank.wbteam.repository;

import com.startuptank.wbteam.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
    List<Project> findByProjectFieldFieldName(String fieldName);
    Project findProjectByProjectId(Integer id);
    
    

    @Query(value = "select * from projects where user_id = ?1",nativeQuery = true)
    List<Project> findAllByUser(long id);

}

