package com.startuptank.wbteam.controller;

import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.entity.ProjectField;
import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.service.serviceImpl.ProjectFileServiceImpl;
import com.startuptank.wbteam.service.serviceImpl.ProjectServiceImpl;
import com.startuptank.wbteam.service.serviceImpl.UserServiceImpl;
import com.startuptank.wbteam.util.CookieUtil;
import com.startuptank.wbteam.util.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProjectServiceImpl projectService;
    @Autowired
    ProjectFileServiceImpl projectFileService;
    @Autowired
    CookieUtil cookieUtil;
    @Autowired
    SessionService sessionService;
    //    get home page
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(Model model) {
        model.addAttribute("projectFields",projectFileService.findAll());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("msc", projectService.findByProjectFieldFieldName("Music Art"));
        model.addAttribute("dig", projectService.findByProjectFieldFieldName("Digital Art"));
        model.addAttribute("blc", projectService.findByProjectFieldFieldName("Blockchain"));
        model.addAttribute("vtr", projectService.findByProjectFieldFieldName("VirtualW"));
        model.addAttribute("vbe", projectService.findByProjectFieldFieldName("Valuable"));
        model.addAttribute("nft", projectService.findByProjectFieldFieldName("Triple NFT"));
        return "user/index";
    }
    //    get login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model, @RequestParam(value = "email", required = false) Optional<String> email,
                           @RequestParam(value = "password", required = false) Optional<String> password) {
        String emailValue = email.orElseGet(() -> String.valueOf(cookieUtil.getCookie("email").orElse("")));
        String passwordValue = password.orElseGet(() -> String.valueOf(cookieUtil.getCookie("password").orElse("")));
        model.addAttribute("email", emailValue);
        model.addAttribute("password", passwordValue);
        return "user/login";
    }
    //    login account
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userService.login(email, password);
        if (user != null) {
            if (userService.activeUser(user)) {
                cookieUtil.setCookie( user.getEmail(),"email",  2);
                cookieUtil.setCookie( user.getPassword(), "password" ,2);
                sessionService.set("UserCurrent", user);
                return "redirect:/home";
            } else {
                model.addAttribute("err", "Account is not active!");
                return "user/login";
            }
        }
        model.addAttribute("err", "Wrong password or email!");
        return "user/login";
    }
    //    logout account
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogOut(){
        sessionService.remove("UserCurrent");
        return "redirect:/home";
    }
    //    get register page
    @RequestMapping(value = "/register")
    public String getRegister(Model model){
        model.addAttribute("user",new User());
        return "user/register";
    }
    //    register account
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute("user") User user,
                           @RequestParam("password") String password,
                           @RequestParam("fullName") String fullname,
                           @RequestParam("email") String email,
                           @RequestParam("re-password") String rePassword){
        if(password.isBlank() || email.isBlank()  || rePassword.isBlank() ) {
            model.addAttribute("errblank","Please is not empty");
            return "user/register";
        }
        if(userService.existsUserByEmail(user.getEmail())) {
            model.addAttribute("errEmail", "Email exist!");
            return "user/register";
        } else{
            if(password.equals(rePassword)){
                userService.save(user);
                sessionService.set("UserCurrent", user);
            }else {
                model.addAttribute("errblank","password erro");
                return "user/register";
            }
        }
        return "redirect:/home";
    }
    //  get all project
    @RequestMapping(value = "/explore")
    public String getExplore(Model model, @RequestParam("category") Optional<String> cate){
        List<Project> projects = projectService.findAll();
        if(cate.isPresent()){
            projects = projectService.findByProjectFieldFieldName(cate.get());
            model.addAttribute("cate", cate.get());
        }
        model.addAttribute("projectfield",projectFileService.findAll());
        model.addAttribute("projects",projects);
        model.addAttribute("projectAll", projectService.findAll());

        return "user/explore";
    }
    //    get detail project
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String getDetailProduct(Model model, @PathVariable("id") Integer id){
        Project project = projectService.findProjectByProjectId(id);
        model.addAttribute("project", project);
        model.addAttribute("pro", projectService.findByProjectFieldFieldName(project.getProjectField().getFieldName()));
        return "user/details";
    }
    //   profile
<<<<<<< HEAD
//    @RequestMapping(value = "/author", method = RequestMethod.GET)
//    public String getAuthor(Model model){
//        return "user/author";
//    }
=======
    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String getAuthor(Model model){
        return "user/author";
    }
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd

    //    create project
    @RequestMapping(value = "/createproject", method = RequestMethod.GET)
    public String getCreateProject(Model model){
        if(sessionService.get("UserCurrent") == null){
            return "user/login";
        }
        model.addAttribute("project", new Project());
        model.addAttribute("projectfield",projectFileService.findAll());
        return "user/create-project";
    }
<<<<<<< HEAD
//    @RequestMapping(value = "/createproject", method = RequestMethod.POST)
//    public String createProject(Model model,
//                                @ModelAttribute("project") Project project, @RequestParam("image") MultipartFile multipartFile){
//        User user = sessionService.get("UserCurrent");
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        project.setProjectImageUrl(fileName);
//
//        project.setUser(user);
//        Project p = projectService.save(project);
//        return "redirect:/home";
//    }

    @RequestMapping(value = "/createproject", method = RequestMethod.POST)
    public String createProject(Model model,
                                @ModelAttribute("project") Project project, @RequestParam("image") MultipartFile multipartFile){
        User user = sessionService.get("UserCurrent");;
        String pImage = multipartFile.getOriginalFilename();
        if (!multipartFile.isEmpty()) {
            File dir = new File("E:\\FPT\\TTTN-FALL2023\\wbteam (1)\\src\\main\\resources\\static\\user\\images");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                File savedFile = new File(dir, multipartFile.getOriginalFilename());
                multipartFile.transferTo(savedFile);
            } catch (Exception e) {
                // TODO: handle exception
                throw new RuntimeException(e);
            }
        }
        if (pImage != "")
            project.setUser(user);
        project.setProjectImageUrl(pImage);
        Project p = projectService.save(project);
        return "redirect:/home";
    }

    private void createUploadDirectory(String uploadDir) {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("Could not create upload directory: " + uploadPath, e);
            }
        }
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String getAuthor(Model model){
        User user = (User) sessionService.get("UserCurrent");
        long id = user.getUserId();
        System.out.println(id);
        List<Project> projects = projectService.findAllbyUser(id);
        model.addAttribute("projects",projects);
        return "user/author";
    }
=======
    @RequestMapping(value = "/createproject", method = RequestMethod.POST)
    public String createProject(Model model,
                                @ModelAttribute("project") Project project, @RequestParam("image") MultipartFile multipartFile){
        User user = sessionService.get("UserCurrent");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        project.setProjectImageUrl(fileName);

        project.setUser(user);
        Project p = projectService.save(project);
        return "redirect:/home";
    }
>>>>>>> 5748ed48fc7f1dfc4097e0da22870fdc6ed853cd

}
