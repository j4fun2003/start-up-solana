package com.startuptank.wbteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/index")
    public String getIndex(){
        return "user/index";
    }
}
