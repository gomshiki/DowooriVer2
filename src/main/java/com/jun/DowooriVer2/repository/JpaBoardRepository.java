package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.DTO.CalendarDTO;
import com.jun.DowooriVer2.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class JpaBoardRepository implements BoardRepository {

    private final EntityManager em;

    /**
     * 부서원 기안문 전체 조회
     */
    @Override
    @Transactional(readOnly = true)
    public List<CalendarDTO> findAllByDept(Long deptNum) {

        String jpql  = "SELECT NEW com.jun.DowooriVer2.DTO.CalendarDTO(b.title, b.startDate, b.endDate, m.userName, b.ampm) " +
                "FROM Board b RIGHT JOIN b.member m ON b.empNum = m.empNum WHERE b.status = '결재완료' AND b.deptNum = :deptNum";

        List<CalendarDTO> boards = em.createQuery(jpql, CalendarDTO.class).setParameter("deptNum", deptNum).getResultList();

        return boards;
    }

    /**
     * 해당 회원의 기안문 전체 조회
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Board> findAll(Long empNum) {

        String sql;

        Object checkSpot = em.createQuery("select m.spot from Member m where m.empNum = :number")
                .setParameter("number", empNum).getSingleResult();

        Object checkDeptNum = em.createQuery("select m.deptNum from Member m where m.empNum = :number")
                .setParameter("number", empNum).getSingleResult();

        if (checkSpot.equals("사원")) {

            sql = "select b from Board b join fetch b.member where b.empNum = :number";
            List<Board> resultList = em.createQuery(sql, Board.class)
                    .setParameter("number", empNum)
                    .getResultList();

            return resultList;

        } else {
            sql = "select b from Board b join fetch b.member where b.empNum = :number or b.approveLevel='부서장' and b.deptNum = :deptNum and b.status in ('결재중','결재완료') order by b.id asc ";
            List<Board> resultList = em.createQuery(sql, Board.class)
                    .setParameter("number", empNum)
                    .setParameter("deptNum", checkDeptNum)
                    .getResultList();

            return resultList;
        }


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
        findBoard.setReason(board.getReason());
        findBoard.setApproveLevel(board.getApproveLevel());
        findBoard.setStatus(board.getStatus());

    }

    @Override
    public int findAllCnt(Long empNum) {

        Object checkSpot = em.createQuery("select m.spot from Member m where m.empNum = :number")
                .setParameter("number", empNum).getSingleResult();

        Object checkDeptNum = em.createQuery("select m.deptNum from Member m where m.empNum = :number")
                .setParameter("number", empNum).getSingleResult();


        // Number로 cast 후 intvalue를 이용해서 object -> int로 변환
        if(checkSpot.equals("사원")){
            return ((Number) em.createQuery("Select count(b.id) from Board b left join b.member where b.empNum = :number")
                    .setParameter("number", empNum)
                    .getSingleResult()).intValue();
        }else{
            return ((Number) em.createQuery("select count(b.id) from Board b left join b.member where b.empNum = :number or " +
                            "b.approveLevel='부서장' and b.deptNum = :deptNum and b.status in ('결재중','결재완료') ")
                    .setParameter("number", empNum)
                    .setParameter("deptNum", checkDeptNum)
                    .getSingleResult()).intValue();
        }



    }


    @Override
    @Transactional(readOnly = true)
    public List<Board> findAllPaging(Long empNum, int startIndex, int pageSize) {

        String sql;

        Object checkSpot = em.createQuery("select m.spot from Member m where m.empNum = :number")
                .setParameter("number", empNum).getSingleResult();

        Object checkDeptNum = em.createQuery("select m.deptNum from Member m where m.empNum = :number")
                .setParameter("number", empNum).getSingleResult();

        if (checkSpot.equals("사원")) {

            sql = "SELECT b FROM Board b JOIN FETCH b.member WHERE b.empNum = :number ORDER BY b.writeDate DESC ";
            List<Board> resultList = em.createQuery(sql, Board.class)
                    .setParameter("number", empNum)
                    .setFirstResult(startIndex)
                    .setMaxResults(pageSize)
                    .getResultList();

            return resultList;

        } else {
            sql = "select b from Board b join fetch b.member where b.empNum = :number or b.approveLevel='부서장' and b.deptNum = :deptNum and b.status in ('결재중','결재완료') ORDER BY b.writeDate DESC ";
            List<Board> resultList = em.createQuery(sql, Board.class)
                    .setParameter("number", empNum)
                    .setParameter("deptNum", checkDeptNum)
                    .setFirstResult(startIndex)
                    .setMaxResults(pageSize)
                    .getResultList();

            for (Board board : resultList) {
                System.out.println("board.getId() = " + board.getId());
            }
            
            return resultList;
        }


    }
}
