package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.DTO.BoardDTO;
import com.jun.DowooriVer2.Session.SessionConst;
import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.service.BoardService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.SourceVersion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board/approve.do")
    public void updateBoard(@RequestParam("boardId") Long boardId){
        boardService.updateBoard(boardId);
    }

    @PostMapping("/board/write.do")
    public HashMap<String, String> writeBoard(HttpServletRequest request, BoardDTO boardDTO){

        /** 값 확인**/
        System.out.println("boardDTO = " + boardDTO);

        
        /** Session을 이용해 로그인 회원정보 가젹오기 **/
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        /** 로그인정보를 이용해 기안문 DB 사번정보 매핑 및 저장 **/
        boardDTO.setEmpNum(String.valueOf(loginMember.getEmpNum()));
        boardDTO.setDeptName(loginMember.getDeptName());
        
        Board board = replaceDTOtoBoard(boardDTO);

        System.out.println("board = " + board);

        boardService.createBoard(board);

        /** hashmap이용해 ajax로 리턴할 데이터 입력**/
        HashMap<String, String> msg = new HashMap<>();
        msg.put("msg", "기안문 저장완료");

        return msg;
    }

    @PostMapping("/board/delete.do")
    public HashMap<String, String> deleteBoard(Board board){

        System.out.println("board.getId() = " + board.getId());

        boardService.deleteBoard(board.getId());

        /** hashmap이용해 ajax로 리턴할 데이터 입력**/
        HashMap<String, String> msg = new HashMap<>();
        msg.put("msg", "기안문 삭제완료");
        return msg;
    }

    @PostMapping("/board/find")
    public Optional<Board> findBoardById(Long deptNum) {

        return boardService.findById(deptNum);
    }

    private Board replaceDTOtoBoard(BoardDTO dto) {

        Board board = new Board();

        board.setAmpm(dto.getAmpm());
        board.setReason(dto.getReason());
        board.setTitle(dto.getTitle());
        board.setStartDate(Date.valueOf(dto.getStartDate()));
        board.setEndDate(Date.valueOf(dto.getEndDate()));
        board.setEmpNum(dto.getEmpNum());
        board.setDeptName(dto.getDeptName());
        board.setStatus("작성중");
        return board;

    }
}