package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.DTO.ChartTeamDTO;
import com.jun.DowooriVer2.DTO.DayoffTeamDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayoffChartRepository {


    List<ChartDTO> dayoffCnt(Long empNum, Long deptNum);

    List<ChartTeamDTO> teamCnt(Long empNum, Long deptNum);

    List<DayoffTeamDTO> totalDayoffCnt();

    List<DayoffTeamDTO> totalDayoffCnt(String deptNum);
}
