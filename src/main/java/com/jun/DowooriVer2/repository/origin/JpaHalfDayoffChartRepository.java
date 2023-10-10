package com.jun.DowooriVer2.repository.origin;

import com.jun.DowooriVer2.dto.ChartDTO;
import com.jun.DowooriVer2.dto.ChartTeamDTO;
import com.jun.DowooriVer2.dto.DayoffTeamDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class JpaHalfDayoffChartRepository implements HalfDayoffChartRepository{

    private final EntityManager em;

    // 로컬 날짜 생성
    LocalDate now = LocalDate.now();

    /**
     * 반차 갯수 조회
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
                "and title = '반차'" +
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
                "and title = '반차'" +
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
                "where year(b.startDate) =: nowYear and title = '반차' " +
                "group by date_format(b.startDate, '%m월'), b.department.deptName";

        List<DayoffTeamDTO> resultList = em.createQuery(sql, DayoffTeamDTO.class)
                .setParameter("nowYear", nowYear)
                .getResultList();

        return resultList;
    }


    // 부서별 반차 조회 기능
    @Override
    public List<DayoffTeamDTO> totalDayoffCnt(String id) {

        // 현재 연도 출력
        int nowYear = now.getYear();

        // 형변환(String -> Long)
        long deptNum = Long.parseLong(id);

        String sql = "select NEW com.jun.DowooriVer2.DTO.DayoffTeamDTO(date_format(b.startDate, '%m월'), b.department.deptName, count(*)) " +
                "from Board b " +
                "where year(b.startDate) =: nowYear " +
                "and title = '반차' " +
                "and b.deptNum =: deptNum " +
                "group by date_format(b.startDate, '%m월'), b.department.deptName";

        List<DayoffTeamDTO> resultList = em.createQuery(sql, DayoffTeamDTO.class)
                .setParameter("nowYear", nowYear)
                .setParameter("deptNum", deptNum)
                .getResultList();


        return resultList;
    }
}
