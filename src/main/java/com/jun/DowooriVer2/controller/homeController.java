package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(){ return "login";}
}
