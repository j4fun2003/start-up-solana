package com.startuptank.wbteam.controller;

import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.entity.ProjectField;
import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.service.ProjectService;
import com.startuptank.wbteam.service.serviceImpl.ProjectFileServiceImpl;
import com.startuptank.wbteam.service.serviceImpl.ProjectServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectServiceImpl projectService;
    @Autowired
    ProjectFileServiceImpl projectFileService;
    @Autowired
    HttpSession session;

        @PostMapping("/buy/{id}")
        public ResponseEntity<String> buyProject(@PathVariable(name = "id") Integer id, HttpSession session) {
            Project project = projectService.findProjectByProjectId(id);

            if (project == null) {
                return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
            }

            User user = (User) session.getAttribute("UserCurrent");

            if (user == null) {
                return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
            }

            project.setUser(user);
            projectService.save(project);

            return new ResponseEntity<>("Project bought successfully", HttpStatus.OK);
        }





}
