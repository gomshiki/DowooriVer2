package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.DTO.BoardDTO;
import com.jun.DowooriVer2.DTO.CalendarDTO;
import com.jun.DowooriVer2.Session.SessionConst;
import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.domain.Pagination;
import com.jun.DowooriVer2.service.BoardService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.SourceVersion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board/approve.do")
    public HashMap<String, String> approveBoard(Board board){

        Board findBoard = boardService.findById(board.getId()).get();

        if(findBoard.getStatus().equals("작성중") && findBoard.getMember().getSpot().equals("사원")){
            findBoard.setApproveLevel("부서장");
            findBoard.setStatus("결재중");
        } else if(findBoard.getApproveLevel().equals("부서장")){
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
        boardDTO.setEmpNum(loginMember.getEmpNum());
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

    @DeleteMapping("/board/delete.do")
    public HashMap<String, String> deleteBoard(Board board){

        System.out.println("board.getId() = " + board.getId());

        boardService.deleteBoard(board.getId());

        /** hashmap이용해 ajax로 리턴할 데이터 입력**/
        HashMap<String, String> msg = new HashMap<>();
        msg.put("msg", "기안문 삭제완료");
        return msg;
    }

    /** 기안문 1개 조회 **/
    @GetMapping("/board/find.do")
    public Optional<Board> findBoardById(Board board) {

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

    @GetMapping("/board/findall.do")
    public List<Map<String, Object>> findAllBoard(HttpServletRequest request) {

        /** Session을 이용해 로그인 회원정보 가젹오기 **/
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<CalendarDTO> Boards = boardService.findAllByDept(loginMember.getDeptNum());

        // HashMap으로 저장된 데이터를 JSON으로 변환할 수 있는 라이브러리
        // 사용방법 1) : JSONObject jsonObj = new JSONObject(hashMap);
        // 사용방법 2) : JSONObject jo = new JSONObject("{\"city\":\"Seoul\",\"name\":\"Jone\"}");
        JSONObject jsonObj;

        // Jsonarray 구조 : [{key : value}, {key : value}]
        // 배열 구조이며, 문자열, 숫자, 배열, 객체 등을 담을 수 있다.
        JSONArray jsonArr = new JSONArray();

        // HashMap : map collection으로 MAp 인터페이스를 상속받음
        // Key, value 모두 객체임
        // put으로 데이터 입력, get(key)로 value 출력함
        HashMap<String, Object> hash = new HashMap<>();

        for (int i = 0; i < Boards.size(); i++) {

            hash.put("title", Boards.get(i).getUserName() + " " + Boards.get(i).getTitle());

            if (Boards.get(i).getTitle().equals("반차") && Boards.get(i).getAmpm().equals("오전")) {
                hash.put("start", Boards.get(i).getStartDate() + "T08:00:00");
                hash.put("end", Boards.get(i).getEndDate() + "T12:00:00");
            } else if (Boards.get(i).getTitle().equals("반차") && Boards.get(i).getAmpm().equals("오후")) {
                hash.put("start", Boards.get(i).getStartDate() + "T13:00:00");
                hash.put("end", Boards.get(i).getEndDate() + "T17:00:00");
            }else {
                hash.put("start", Boards.get(i).getStartDate());
                hash.put("end", Boards.get(i).getEndDate());
            }

            // hashmap을 JSON화하기
            jsonObj = new JSONObject(hash);

            // Array형태로 만들기 => [ {key : value}, {key : value} ]
            jsonArr.add(jsonObj);
        }
        log.info("jsonArrCheck: {}", jsonArr);
        return jsonArr;

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

        board.setEmpNum(dto.getEmpNum());
        board.setDeptNum(dto.getDeptNum());
        board.setStatus("작성중");
        return board;

    }

}
