package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.DTO.ChartTeamDTO;
import com.jun.DowooriVer2.Session.SessionConst;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.service.BoardService;
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

    public ChartController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
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
        String userName = loginMember.getUserName();


        List<ChartDTO> chartDTOS = boardService.dayoffCnt(empNum, deptNum); // 개인 연차 사용량
        List<ChartTeamDTO> dayoffTeamCnts = boardService.dayoffTeamCnt(empNum, deptNum); // 부서원 간 연차 사용량

        log.info("dayoff teams >> " + dayoffTeamCnts.toString()); // 결과 로그 확인

        List<String> months = new ArrayList<>();
        List<Long> totalCnts = new ArrayList<>();

        for (ChartDTO chartDTO : chartDTOS) {
            String month = chartDTO.getMonths();
            Long totalCnt = chartDTO.getTotalCnt();
            months.add(month);
            totalCnts.add(totalCnt);
        }

        log.info("totalCnts: " + totalCnts);
        log.info("months: " + months);

        model.addAttribute("totalCnts", totalCnts);
        model.addAttribute("months", months);

        model.addAttribute("member", loginMember);

        return "monthStatics";

    }


}
