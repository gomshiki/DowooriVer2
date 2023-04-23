package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.DTO.CalendarDTO;
import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.DTO.ChartTeamDTO;
import com.jun.DowooriVer2.DTO.DayoffTeamDTO;
import com.jun.DowooriVer2.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class JpaBoardRepository implements BoardRepository {

    // 로컬 날짜 생성
    LocalDate now = LocalDate.now();



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
            sql = "select b from Board b join fetch b.member " +
                    "where b.empNum = :number or b.approveLevel='부서장' and b.deptNum = :deptNum and b.status in ('결재중','결재완료') " +
                    "order by b.id asc ";
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

        // 반차를 수정할 경우 startDate을 EndDate로 지정
        if(board.getTitle().equals("반차")){
            findBoard.setEndDate(board.getStartDate());
        }else {
            findBoard.setEndDate(board.getEndDate());
        }

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
            sql = "select b from Board b join fetch b.member where b.empNum = :number " +
                    "or b.approveLevel='부서장' and b.deptNum = :deptNum and b.status in ('결재중','결재완료') " +
                    "ORDER BY b.writeDate DESC ";
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


    /**
     * 연차 갯수 조회
     *
     * @return
     */
    @Override
    public List<ChartDTO> dayoffCnt(Long empNum, Long deptNum) {


        // 현재 연도 출력
        int nowYear = now.getYear();


        String sql;

        sql = "select NEW com.jun.DowooriVer2.DTO.ChartDTO(date_format(b.startDate, '%m월'), count(b)) " +
                "from Board b " +
                "where b.empNum = :empNum and b.deptNum = :deptNum " +
                "and year(b.startDate) = :nowYear " +
                "and title = '연차'" +
                "group by date_format(b.startDate, '%m월')"
        ;

        List<ChartDTO> resultList = em.createQuery(sql, ChartDTO.class)
                .setParameter("empNum", empNum)
                .setParameter("deptNum", deptNum)
                .setParameter("nowYear", nowYear)
                .getResultList();

        return resultList;


    }

    @Override
    public List<ChartTeamDTO> teamCnt(Long empNum, Long deptNum) {

        // 현재 연도 출력
        int nowYear = now.getYear();


        String sql;

        sql = "select NEW com.jun.DowooriVer2.DTO.ChartTeamDTO(b.member.userName , count(*))" +
                "from Board b " +
                "where b.deptNum = :deptNum " +
                "and year(b.startDate) =: nowYear " +
                "and title = '연차'" +
                "group by b.member.userName";

        List<ChartTeamDTO> resultList = em.createQuery(sql, ChartTeamDTO.class)
                .setParameter("deptNum", deptNum)
                .setParameter("nowYear", nowYear)
                .getResultList();


        return resultList;
    }

    @Override
    public List<DayoffTeamDTO> totalDayoffCnt() {

        // 현재 연도 출력
        int nowYear = now.getYear();

        String sql = "select NEW com.jun.DowooriVer2.DTO.DayoffTeamDTO(date_format(b.startDate, '%m월'), b.department.deptName , count(*)) " +
                "from Board b " +
                "where year(b.startDate) =: nowYear and title = '연차' " +
                "group by date_format(b.startDate, '%m월'), b.department.deptName";

        List<DayoffTeamDTO> resultList = em.createQuery(sql, DayoffTeamDTO.class)
                                        .setParameter("nowYear", nowYear)
                                        .getResultList();

        return resultList;
    }

    @Override
    public List<DayoffTeamDTO> totalDayoffCnt(String id) {

        // 현재 연도 출력
        int nowYear = now.getYear();

        // 형변환(String -> Long)
        long deptNum = Long.parseLong(id);

        String sql = "select NEW com.jun.DowooriVer2.DTO.DayoffTeamDTO(date_format(b.startDate, '%m월'), b.department.deptName, count(*)) " +
                "from Board b " +
                "where year(b.startDate) =: nowYear " +
                "and title = '연차' " +
                "and b.deptNum =: deptNum " +
                "group by date_format(b.startDate, '%m월'), b.department.deptName";

        List<DayoffTeamDTO> resultList = em.createQuery(sql, DayoffTeamDTO.class)
                .setParameter("nowYear", nowYear)
                .setParameter("deptNum", deptNum)
                .getResultList();

        return resultList;
    }
}
