package com.jun.DowooriVer2.repository.origin;

import com.jun.DowooriVer2.dto.ChartDTO;
import com.jun.DowooriVer2.dto.ChartTeamDTO;
import com.jun.DowooriVer2.dto.DayoffTeamDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DayoffChartRepository {


    List<ChartDTO> dayoffCnt(Long empNum, Long deptNum);

    List<ChartTeamDTO> teamCnt(Long empNum, Long deptNum);

    List<DayoffTeamDTO> totalDayoffCnt();

    List<DayoffTeamDTO> totalDayoffCnt(String deptNum);
}
