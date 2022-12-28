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

//        String sqlQuery = "insert into board_table values(ampm =: ampm, dept_name, emp_num, end_date, reason, start_date, status, title)";
//        System.out.println(sqlQuery);


        em.persist(board);
    }
}
