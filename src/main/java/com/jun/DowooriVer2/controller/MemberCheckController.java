//package com.jun.DowooriVer2.controller;
//
//import com.jun.DowooriVer2.domain.Member;
//import com.jun.DowooriVer2.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//public class MemberCheckController {
//
//    // autowired : bean으로 등록된 클래스들을 스프링이 시작할때 자동으로 주입
//    @Autowired
//    private final MemberService memberService;
//
//    public MemberCheckController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//
//
//    @PostMapping("/register.do")
//    public String joinUs(@ModelAttribute("member") Member member){
//
//        memberService.join(member);
//
//        return "index";
//    }
//
//    @PostMapping("/register.userEmail")
//    public Optional<Member> existsByUserEmail(@RequestParam("email") String userEmail){
//
//        return memberService.existsByUserEmail(userEmail);
//    }
//
//}
