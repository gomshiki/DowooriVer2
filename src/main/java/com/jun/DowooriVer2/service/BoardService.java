package com.jun.DowooriVer2.service;

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
@Transactional(readOnly = true)
public class BoardService {

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll(Long empNum){
        return boardRepository.findAll(empNum);
    }

}
