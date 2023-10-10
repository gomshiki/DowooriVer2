package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.dto.ChartDTO;
import com.jun.DowooriVer2.dto.ChartTeamDTO;
import com.jun.DowooriVer2.Session.SessionConst;
import com.jun.DowooriVer2.domain.Department;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class ChartController {

    private final DayoffChartService dayoffChartService;
    private final HalfDayoffChartService halfDayoffChartService;
    private final DeptService  deptService;

    public ChartController(DayoffChartService dayoffChartService, HalfDayoffChartService halfDayoffChartService, DeptService deptService) {
        this.dayoffChartService = dayoffChartService;
        this.halfDayoffChartService = halfDayoffChartService;
        this.deptService = deptService;
    }

    /*
    *  1. 월별 연차 사용량 통계
    *  */
    @GetMapping("/staticsMonthDayoff")
    public String selectDayoffCnt(Model model, HttpServletRequest request){

        HttpSession session = request.getSession(false); //false : 새로 생성 X

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원데이터가 없으면 로그인창으로
        if (loginMember == null) {
            return "/";
        }

        log.info("loginMember.getEmpNum >> " + loginMember.getEmpNum());

        Long empNum = loginMember.getEmpNum();
        Long deptNum = loginMember.getDeptNum();

        List<ChartDTO> chartDTOS = dayoffChartService.dayoffCnt(empNum, deptNum); // 개인 연차 사용량
        List<ChartTeamDTO> dayoffTeamCnts = dayoffChartService.teamCnt(empNum, deptNum); // 부서원 간 연차 사용량

        log.info("dayoff teams >> " + dayoffTeamCnts.toString()); // 팀 연차 결과 로그 확인

        List<Department> depts = deptService.selectAllDept();


        model.addAttribute("member", loginMember);
        model.addAttribute("chartDTOS", chartDTOS);
        model.addAttribute("dayoffTeamCnts", dayoffTeamCnts);
        model.addAttribute("depts", depts);

        return "staticsMonthDayoff";

    }

    /*
     *  2. 월별 반차 사용량 통계
     *  */
    @GetMapping("/staticsMonthHalfDayoff")
    public String selectHalfDayoffCnt(Model model, HttpServletRequest request){

        HttpSession session = request.getSession(false); //false : 새로 생성 X

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원데이터가 없으면 로그인창으로
        if (loginMember == null) {
            return "/";
        }

        log.info("loginMember.getEmpNum >> " + loginMember.getEmpNum());

        Long empNum = loginMember.getEmpNum();
        Long deptNum = loginMember.getDeptNum();

        List<ChartDTO> chartDTOS = halfDayoffChartService.dayoffCnt(empNum, deptNum); // 개인 연차 사용량
        List<ChartTeamDTO> dayoffTeamCnts = halfDayoffChartService.teamCnt(empNum, deptNum); // 부서원 간 연차 사용량

        log.info("dayoff teams >> " + dayoffTeamCnts.toString()); // 팀 연차 결과 로그 확인

        List<Department> depts = deptService.selectAllDept();


        model.addAttribute("member", loginMember);
        model.addAttribute("chartDTOS", chartDTOS);
        model.addAttribute("dayoffTeamCnts", dayoffTeamCnts);
        model.addAttribute("depts", depts);

        return "staticsMonthHalfDayoff";

    }



}
