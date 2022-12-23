package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.Session.SessionConst;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class homeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String login(Model model) {

        memberService.deleteMember(); // Member 데이터 초기화
        List<Member> members = memberService.save();
        model.addAttribute("members", members);
        model.addAttribute("member", new Member());

        return "login";
    }

    @GetMapping("/home")
    public String homeLogin(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false); //false : 새로 생성 X

        if(session == null){
            return "/";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원데이터가 없으면 로그인창으로
        if(loginMember == null){
            return "/";
        }
        model.addAttribute("member", loginMember);

        return "home";
    }

    @GetMapping("/tables")
    public String table(){
        return "tables";
    }

}
