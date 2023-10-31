package com.startuptank.wbteam.controller;

import com.startuptank.wbteam.entity.Project;
import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.service.serviceImpl.ProjectFileServiceImpl;
import com.startuptank.wbteam.service.serviceImpl.ProjectServiceImpl;
import com.startuptank.wbteam.service.serviceImpl.UserServiceImpl;
import com.startuptank.wbteam.util.CookieUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    HttpSession session ;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model, @RequestParam(value = "email", required = false) Optional<String> email,
                           @RequestParam(value = "password", required = false) Optional<String> password) {
        String emailValue = email.orElseGet(() -> String.valueOf(cookieUtil.getCookie("email").orElse("")));
        String passwordValue = password.orElseGet(() -> String.valueOf(cookieUtil.getCookie("password").orElse("")));
        model.addAttribute("email", emailValue);
        model.addAttribute("password", passwordValue);
        return "user/login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogOut(HttpSession session){
        session.removeAttribute("UserCurrent");
        return "redirect:/home";
    }

    @RequestMapping(value = "/register")
    public String getRegister(Model model){
        model.addAttribute("user",new User());
        return "user/register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute("user") User user,
                           @RequestParam("password") String password,
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
                session.setAttribute("UserCurrent",user);
            }else {
                model.addAttribute("errblank","password erro");
                return "user/register";
            }
        }
        return "redirect:/home";
    }

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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userService.login(email, password);
        if (user != null) {
            if (userService.activeUser(user)) {
                cookieUtil.setCookie( user.getEmail(),"email",  2);
                cookieUtil.setCookie( user.getPassword(), "password" ,2);
                session.setAttribute("UserCurrent", user);
                return "redirect:/home";
            } else {
                model.addAttribute("err", "Account is not active!");
                return "user/login";
            }
        }
        model.addAttribute("err", "Wrong password or email!");
        return "user/login";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String getDetailProduct(Model model, @PathVariable("id") Integer id){
        Project project = projectService.findProjectByProjectId(id);
        model.addAttribute("project", project);
        model.addAttribute("pro", projectService.findByProjectFieldFieldName(project.getProjectField().getFieldName()));
        return "user/details";
    }

}
