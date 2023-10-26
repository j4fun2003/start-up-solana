package com.startuptank.wbteam.controller;

import com.startuptank.wbteam.entity.User;
import com.startuptank.wbteam.service.serviceImpl.UserServiceImpl;
import com.startuptank.wbteam.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CookieUtil cookieUtil;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(@RequestParam("email") Optional<String> email, @RequestParam("password")  Optional<String> password){
           email.orElse(String.valueOf(cookieUtil.getCookie("email")));
            password.orElse(String.valueOf(cookieUtil.getCookie("password")));
       return "user/login";
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(){
        return "user/index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("email") String email, @RequestParam("password") String password){
       User user =  userService.login(email,password);
       if(user != null){
           if(userService.activeUser(user)){
               cookieUtil.setCookie(user.getEmail(),"email",2);
               cookieUtil.setCookie(user.getPassword(),"password",2);
               return "redirect:/home";
           }else {
               model.addAttribute("err","Account is not active!");
               return "user/login";
           }
       }
       model.addAttribute("err","Wrong password or email!");
       return "user/login";

    }

}
