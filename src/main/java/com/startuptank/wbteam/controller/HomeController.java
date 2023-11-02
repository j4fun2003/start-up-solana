package com.startuptank.wbteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/home/index")
    public String index() {
        return "user/index";
    }
}
