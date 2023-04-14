package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.DTO.CalendarDTO;
import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
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


    public List<ChartDTO> dayoffCnt(Long empNum, Long deptNum) {

        return boardRepository.dayoffCnt(empNum, deptNum);

    }
}
