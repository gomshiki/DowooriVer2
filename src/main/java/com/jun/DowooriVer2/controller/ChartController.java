package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.DTO.ChartTeamDTO;
import com.jun.DowooriVer2.Session.SessionConst;
import com.jun.DowooriVer2.domain.Department;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.service.BoardService;
import com.jun.DowooriVer2.service.DeptService;
import com.jun.DowooriVer2.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ChartController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final DeptService  deptService;

    public ChartController(BoardService boardService, MemberService memberService, DeptService deptService) {
        this.boardService = boardService;
        this.memberService = memberService;
        this.deptService = deptService;
    }

    /*
    *  1. 연차 사용 횟수 차트 용 함수
    *  */
    @GetMapping("/staticsMonth")
    public String selectBoardCnt(Model model, HttpServletRequest request){

        HttpSession session = request.getSession(false); //false : 새로 생성 X

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원데이터가 없으면 로그인창으로
        if (loginMember == null) {
            return "/";
        }

        log.info("loginMember.getEmpNum >> " + loginMember.getEmpNum());

        Long empNum = loginMember.getEmpNum();
        Long deptNum = loginMember.getDeptNum();

        List<ChartDTO> chartDTOS = boardService.dayoffCnt(empNum, deptNum); // 개인 연차 사용량
        List<ChartTeamDTO> dayoffTeamCnts = boardService.dayoffTeamCnt(empNum, deptNum); // 부서원 간 연차 사용량

        log.info("dayoff teams >> " + dayoffTeamCnts.toString()); // 팀 연차 결과 로그 확인

        List<Department> depts = deptService.totalCntDept();


        model.addAttribute("member", loginMember);
        model.addAttribute("chartDTOS", chartDTOS);
        model.addAttribute("dayoffTeamCnts", dayoffTeamCnts);
        model.addAttribute("depts", depts);

        return "monthStatics";

    }


}
