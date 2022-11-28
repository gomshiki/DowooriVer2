package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.repository.MemberRepository;
import com.jun.DowooriVer2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MemberController {
    // autowired : bean으로 등록된 클래스들을 스프링이 시작할때 자동으로 주입
    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // 회원가입
    @GetMapping("/register/join")
    public String register(Model model, Member member) {
        model.addAttribute("member",member);
        return "register";
    }

    @PostMapping("/register/new")
    public String joinUs(@ModelAttribute("member") Member member){

        memberService.join(member);

        return "index";
    }


}
