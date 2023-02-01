package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.DTO.BoardDTO;
import com.jun.DowooriVer2.DTO.CalendarDTO;
import com.jun.DowooriVer2.domain.Board;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository {

    @Transactional(readOnly = true)
    List<CalendarDTO> findAllByDept(Long deptNum);

    List<Board> findAll(Long empNum);

    void createBoard(Board board);

    void deleteBoard(Long deptNum);

    Optional<Board> findById(Long boardId);

    void updateBoard(Board board);

}
