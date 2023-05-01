package com.jun.DowooriVer2.controller;


import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.DTO.DayoffTeamDTO;
import com.jun.DowooriVer2.service.BoardService;
import com.jun.DowooriVer2.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ChartApiController {

    private BoardService boardService;
    private MemberService memberService;

    public ChartApiController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    };

    @GetMapping("/dayoff/select.do")
    public Map selectTeamTotalCntDayoff(HttpServletRequest request) throws Exception {

        String deptNum = request.getParameter("id");

        Map<String, Object> resultMap = new HashMap<String, Object>();

        log.info("deptNum >> " +  deptNum);

        try {

            if(deptNum.equals("AllDept")){
                List resultList =  boardService.allDeptTotalDayoff();
                log.info("resultList >> " +  resultList.toString());
                resultMap.put("resultList", resultList);
                resultMap.put("msg", "success");

            }else{
                List resultList = boardService.totalDayoff(deptNum);
                log.info("resultList >> " +  resultList.toString());
                resultMap.put("resultList",resultList);
                resultMap.put("msg", deptNum +"부서 조회 성공");
            }

        } catch(Exception e){
            log.error("에러 발생!");
            e.printStackTrace();
            resultMap.put("msg","에러발생");
        }



        return resultMap;
    };

    @GetMapping("/halfdayoff/select.do")
    public Map selectTeamTotalCntHalfDayoff(HttpServletRequest request) throws Exception {

        String deptNum = request.getParameter("id");

        Map<String, Object> resultMap = new HashMap<String, Object>();

        log.info("deptNum >> " +  deptNum);

        try {

            if(deptNum.equals("AllDept")){
                List resultList =  boardService.allDeptTotalDayoff();
                log.info("resultList >> " +  resultList.toString());
                resultMap.put("resultList", resultList);
                resultMap.put("msg", "success");

            }else{
                List resultList = boardService.totalDayoff(deptNum);
                log.info("resultList >> " +  resultList.toString());
                resultMap.put("resultList",resultList);
                resultMap.put("msg", deptNum +"부서 조회 성공");
            }

        } catch(Exception e){
            log.error("에러 발생!");
            e.printStackTrace();
            resultMap.put("msg","에러발생");
        }



        return resultMap;
    };
}
