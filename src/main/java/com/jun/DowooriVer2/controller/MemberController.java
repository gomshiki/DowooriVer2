//package com.jun.DowooriVer2.controller;
//
//import com.jun.DowooriVer2.domain.Member;
//import com.jun.DowooriVer2.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@Controller
//public class MemberController {
//
//    // 회원가입 시 데이터를 담을 객체(model) 전달)
//    @GetMapping("/register")
//    public String register(Model model, Member member) {
//        model.addAttribute("member",member);
//        return "register";
//    }
//
//
//}
