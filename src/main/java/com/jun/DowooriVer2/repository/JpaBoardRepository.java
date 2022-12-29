package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.DTO.BoardDTO;
import com.jun.DowooriVer2.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class JpaBoardRepository implements BoardRepository {

    private final EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Board> findAll(Long empNum) {

        TypedQuery<Board> queryResult = em.createQuery("select b from Board b where b.empNum = :number", Board.class)
                .setParameter("number", String.valueOf(empNum)); // Long -> String으로!!

        List<Board> results = queryResult.getResultList();

        return results;
    }

    @Override
    public void createBoard(Board board) {
        em.persist(board);
    }

    @Override
    public void deleteBoard(Long boardId) {
        log.info("repository에서 받은 bordId", boardId);

        Board board = em.find(Board.class, boardId);
        em.remove(board);
    }

    @Override
    public Optional<Board> findById(Long id) {
        Board board = em.find(Board.class, id);
        return Optional.ofNullable(board);
    }

    @Override
    public void updateBoard(Long boardId) {
        Board board = em.find(Board.class, boardId);

    }
}
