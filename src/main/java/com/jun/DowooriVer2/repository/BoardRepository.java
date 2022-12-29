package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.DTO.BoardDTO;
import com.jun.DowooriVer2.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository {

    List<Board> findAll(Long empNum);

    void createBoard(Board board);

    void deleteBoard(Long deptNum);

    Optional<Board> findById(Long boardId);

    void updateBoard(Long boardId);
}
