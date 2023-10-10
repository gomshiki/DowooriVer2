package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.Session.SessionConst;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @PostMapping("/login.do")
    public String login(@RequestParam("empNumber") Long empNumber, HttpServletRequest request) {


        // 회원정보 조회
        Optional<Member> loginMembers = memberService.findById(empNumber);
        Member loginMember = loginMembers.get();

        // 로그인 성공처리
        // 세션이 있으면 기존 세션 반환, 없으면 신규 세션 생성 후 반환
        HttpSession session = request.getSession();

        // 세션 로그인 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate(); //세션안의 데이터를 지워버림
        }

        return "redirect:/selectAccount";
    }
}
