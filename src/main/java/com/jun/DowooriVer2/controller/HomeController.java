package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.DTO.homeDTO;
import com.jun.DowooriVer2.Session.SessionConst;
import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.domain.Pagination;
import com.jun.DowooriVer2.service.BoardService;
import com.jun.DowooriVer2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping("/")
    public String selectAccount(Model model) {

        List<homeDTO> homeDTOS = memberService.findAll();

        model.addAttribute("homeDTOS", homeDTOS);
        model.addAttribute("member", new Member());

        return "selectAccount";
    }

    @GetMapping("/home")
    public String homeLogin(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") int page) throws ParseException {


        HttpSession session = request.getSession(false); //false : 새로 생성 X

        if (session == null) {
            return "/";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원데이터가 없으면 로그인창으로
        if (loginMember == null) {
            return "/";
        }

        log.info("loginMember.getEmpNum >> " + loginMember.getEmpNum());

        Long empNum = loginMember.getEmpNum();

        // List<Board> boards = boardService.findAll(empNum);

        // 페이징

        // 1. 총 게시물 수
        int totalListCnt = boardService.findAllCnt(empNum);

        // 2. 생성인자로  총 게시물 수, 현재 페이지를 전달
        Pagination pagination = new Pagination(totalListCnt, page);


        // DB select start index
        int startIndex = pagination.getStartIndex();

        // 페이지 당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getPageSize();

        List<Board> boards = boardService.findAllPaging(empNum, startIndex, pageSize);

        model.addAttribute("pagination", pagination);
        model.addAttribute("boards", boards);
        model.addAttribute("member", loginMember);
        model.addAttribute("newBoard", new Board());

        return "home";
    }

    @GetMapping("/board")
    public String boardView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); //false : 새로 생성 X

        if (session == null) {
            return "/";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원데이터가 없으면 로그인창으로
        if (loginMember == null) {
            return "/";
        }

        Long empNum = loginMember.getEmpNum();

        List<Board> boards = boardService.findAll(empNum);


        model.addAttribute("boards", boards);
        model.addAttribute("member", loginMember);
        model.addAttribute("newBoard", new Board());

        return "board";
    }

}
