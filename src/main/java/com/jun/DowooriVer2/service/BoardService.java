package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.DTO.BoardDTO;
import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.repository.BoardRepository;
import com.jun.DowooriVer2.repository.JpaBoardRepository;
import com.jun.DowooriVer2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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

    public void approveBoard(Board board) {
        boardRepository.approveBoard(board);
    }
}
