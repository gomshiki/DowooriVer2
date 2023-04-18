package com.jun.DowooriVer2.controller;


import com.jun.DowooriVer2.service.BoardService;
import com.jun.DowooriVer2.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ChartAPIController {

    private BoardService boardService;
    private MemberService memberService;

    public ChartAPIController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    };

    public Map selectTeamTotalCnt(Model model, HttpServletRequest request) {
        return null;
    };
}
