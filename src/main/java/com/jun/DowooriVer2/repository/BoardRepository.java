package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.DTO.CalendarDTO;
import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.DTO.ChartTeamDTO;
import com.jun.DowooriVer2.DTO.DayoffTeamDTO;
import com.jun.DowooriVer2.domain.Board;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository {

    @Transactional(readOnly = true)
    List<CalendarDTO> findAllByDept(Long deptNum);

    @Transactional(readOnly = true)
    List<Board> findAll(Long empNum);

    void createBoard(Board board);

    void deleteBoard(Long deptNum);

    Optional<Board> findById(Long boardId);

    void updateBoard(Board board);

    @Transactional(readOnly = true)
    int findAllCnt(Long empNum);

    @Transactional(readOnly = true)
    List<Board> findAllPaging(Long empNum, int startIndex, int pageSize);

    List<ChartDTO> dayoffCnt(Long empNum, Long deptNum);

    List<ChartTeamDTO> teamCnt(Long empNum, Long deptNum);

    List<DayoffTeamDTO> totalDayoffCnt();

    List<DayoffTeamDTO> totalDayoffCnt(String deptNum);
}
