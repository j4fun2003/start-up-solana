package com.startuptank.wbteam.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.startuptank.wbteam.constant.InvestmentType;
import com.startuptank.wbteam.constant.ProjectStatus;
import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.entity.ProjectField;
import com.startuptank.wbteam.service.ProjectFieldService;
import com.startuptank.wbteam.service.ProjectService;

@Controller
public class ProjectControoler {

    @Autowired
    ProjectFieldService projectFieldService;

    @Autowired
    ProjectService projectService;

    @RequestMapping("/project")
    public String create(Model model) {
        List<ProjectField> jobTypes = projectFieldService.findAll();
        model.addAttribute("jobTypes", jobTypes);
        model.addAttribute("projectStatus",ProjectStatus.values());
        model.addAttribute("investmentType",InvestmentType.values());

        return "user/create-project";
    }

    @PostMapping("/create")
    public String create(@RequestParam("title") String title,
            @RequestParam("jobType") String jobType, @RequestParam("funding") Double funding,
            @RequestParam("deadline") @DateTimeFormat(pattern="yyyy-MM-dd")  Date deadline, @RequestParam("image") MultipartFile image,
            @RequestParam("video") MultipartFile video, @RequestParam("description") String description,
            @RequestParam("status") String status,@RequestParam("investment") String type) {

        ProjectField pf = projectFieldService.findById(Integer.parseInt(jobType));
        Project p = new Project();
        ProjectStatus ps = ProjectStatus.valueOf(status.toUpperCase());
        InvestmentType it = InvestmentType.valueOf(type.toUpperCase());
        p.setProjectName(title);
        p.setProjectField(pf);
        p.setFundingGoal(funding); 
        p.setDeadline(deadline);
        p.setContactEmail("test");
        p.setProjectStatus(ps);
        p.setInvestmentType(it);
        p.setProjectDescription(description);
        String pImage = image.getOriginalFilename();
        if (!image.isEmpty()) {
            File dir = new File("D:\\HomeWork\\blockchain\\start-up-solana\\src\\main\\resources\\static\\user\\project\\img");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                File savedFile = new File(dir, image.getOriginalFilename());
                image.transferTo(savedFile);
            } catch (Exception e) {
                // TODO: handle exception
                throw new RuntimeException(e);
            }
        }
        if (pImage != "")
            p.setProjectImageUrl(pImage);
        String pVideo = video.getOriginalFilename();
        if (!video.isEmpty()) {
            File dir = new File("D:\\HomeWork\\blockchain\\start-up-solana\\src\\main\\resources\\static\\user\\project\\video");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                File savedFile = new File(dir, video.getOriginalFilename());
                video.transferTo(savedFile);
            } catch (Exception e) {
                // TODO: handle exception
                throw new RuntimeException(e);

            }
        }
        if (pVideo != "")
            p.setProjectVideoUrl(pVideo);

        projectService.update(p);
        return "forward:/project";
    }
}
