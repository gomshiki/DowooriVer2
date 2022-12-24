package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaBoardRepository implements BoardRepository {

    private final EntityManager em;

    @Override
    public List<Board> findAll(Long empNum) {

        TypedQuery<Board> queryResult = em.createQuery("select b from Board b where b.empNum = :number", Board.class)
                .setParameter("number", String.valueOf(empNum)); // Long -> String으로!!

        List<Board> results = queryResult.getResultList();

        for (Board result : results) {
            System.out.println("result = " + result);
        }
        return results;
    }
}
