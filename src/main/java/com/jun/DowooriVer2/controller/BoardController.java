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

    @GetMapping("/board/approve.do")
    public HashMap<String, String> approveBoard(Board board){

        Board findBoard = boardService.findById(board.getId()).get();

        if(findBoard.getApproveLevel().equals("사원")){
            findBoard.setApproveLevel("부서장");
        }

        if(findBoard.getStatus().equals("작성중")){
            findBoard.setStatus("결재중");
        }
        if(findBoard.getApproveLevel().equals("부서장") && findBoard.getStatus().equals("결재중")){
            findBoard.setStatus("결재완료");
        }
        if (findBoard.getApproveLevel().equals("작성중") && findBoard.getApproveLevel().equals("부서장")) {
            findBoard.setStatus("결재완료");
        }

        boardService.updateBoard(findBoard);

        /** hashmap이용해 ajax로 리턴할 데이터 입력**/
        HashMap<String, String> msg = new HashMap<>();

        msg.put("mgs", "기안문 결재 성공");

        return msg;

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
        boardDTO.setDeptNum(loginMember.getDeptNum());
        
        Board board = replaceDTOtoBoard(boardDTO);
        board.setApproveLevel(loginMember.getSpot());
        board.setStatus("작성중");

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

    /** 기안문 1개 조회 **/
    @PostMapping("/board/find.do")
    public Optional<Board> findBoardById(Board board) {

        System.out.println("board.getId = " + board.getId());

        return boardService.findById(board.getId());

    }

    @PostMapping("/board/update.do")
    public HashMap<String, String> updateBoard(HttpServletRequest request, BoardDTO boardDTO) {

        /** 값 확인**/
        System.out.println("boardDTO = " + boardDTO);

        /** Session을 이용해 로그인 회원정보 가젹오기 **/
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

//        /** 로그인정보를 이용해 기안문 DB 사번정보 매핑 및 저장 **/
//        boardDTO.setEmpNum(String.valueOf(loginMember.getEmpNum()));
//        boardDTO.setDeptName(loginMember.getDeptName());

        Board board = replaceDTOtoBoard(boardDTO);

        System.out.println("board = " + board);

        boardService.updateBoard(board);

        /** hashmap이용해 ajax로 리턴할 데이터 입력 **/
        HashMap<String, String> msg = new HashMap<>();
        msg.put("msg", "기안문 저장완료");

        return msg;
    }

    /** DTO를 Board에 맞게 수정하는 함수 **/
    private Board replaceDTOtoBoard(BoardDTO dto) {

        Board board = new Board();

        if(dto.getId() != null){
            board.setId(dto.getId());
        }

        board.setAmpm(dto.getAmpm());
        board.setReason(dto.getReason());
        board.setTitle(dto.getTitle());
        board.setStartDate(Date.valueOf(dto.getStartDate()));

        // 반차 : 종료일 == 시작일
        if(dto.getEndDate().isEmpty() || dto.getEndDate().length() == 0 || dto.getEndDate() == null) {
            board.setEndDate(Date.valueOf(dto.getStartDate()));
        }else{
            board.setEndDate(Date.valueOf(dto.getEndDate()));
        }

  //      board.setEmpNum(dto.getEmpNum());
        board.setDeptNum(dto.getDeptNum());
        board.setStatus("작성중");
        return board;

    }
}
