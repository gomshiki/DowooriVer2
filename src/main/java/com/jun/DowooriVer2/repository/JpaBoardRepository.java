package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.DTO.BoardDTO;
import com.jun.DowooriVer2.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
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

    /** 해당 회원의 작성된 기안문 전체 조회**/
    @Override
    @Transactional(readOnly = true)
    public List<Board> findAll(Long empNum) {

        TypedQuery<Board> queryResult = em.createQuery("select b from Board b where b.empNum = :number", Board.class)
                .setParameter("number", String.valueOf(empNum)); // Long -> String으로!!

        List<Board> results = queryResult.getResultList();

        return results;
    }

    /** 기안문 작성 **/
    @Override
    @Nullable
    public void createBoard(Board board) {
        em.persist(board);
    }

    /** 기안문 삭제 **/
    @Override
    public void deleteBoard(Long boardId) {
        log.info("repository에서 받은 bordId", boardId);

        Board board = em.find(Board.class, boardId);
        em.remove(board);
    }

    /** 기안문 1개 조회 **/
    @Override
    public Optional<Board> findById(Long id) {
        Board board = em.find(Board.class, id);
        return Optional.ofNullable(board);
    }

    /** 기안문 수정(변경감지 기능사용) **/
    @Override
    public void updateBoard(Board board) {         //board: 파리미터로 넘어온 준영속 상태의 엔티티
        Board findBoard = em.find(Board.class, board.getId()); //같은 엔티티 조회한다.

        // 데이터 수정
        findBoard.setTitle(board.getTitle());
        findBoard.setStartDate(board.getStartDate());
        findBoard.setEndDate(board.getEndDate());
        findBoard.setAmpm(board.getAmpm());

    }
}
