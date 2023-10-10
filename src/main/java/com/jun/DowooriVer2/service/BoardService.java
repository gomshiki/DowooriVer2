package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.dto.CalendarDTO;
import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.repository.BoardRepository;
import com.jun.DowooriVer2.repository.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
public class BoardService {

    private BoardRepository boardRepository;
    private DeptRepository deptRepository;

    public BoardService(BoardRepository boardRepository, DeptRepository deptRepository) {
        this.boardRepository = boardRepository;
        this.deptRepository = deptRepository;
    }

    public List<Board> findAll(Long empNum){
        return boardRepository.findAll(empNum);
    }

    public void createBoard(Board board) {
        boardRepository.createBoard(board);
    }

    public void deleteBoard(Long deptNum) {
        boardRepository.deleteBoard(deptNum);
    }

    public Optional<Board> findById(Long boardId){
        return boardRepository.findById(boardId);
    }

    public void updateBoard(Board board) {
        boardRepository.updateBoard(board);
    }

    public List<CalendarDTO> findAllByDept(Long deptNum) {
        return boardRepository.findAllByDept(deptNum);}

    public int findAllCnt(Long empNum) {
        return boardRepository.findAllCnt(empNum);
    }

    public List<Board> findAllPaging(Long empNum, int startIndex, int pageSize) {
        return boardRepository.findAllPaging(empNum, startIndex, pageSize);
    }


}
