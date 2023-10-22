package com.jun.DowooriVer2.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String title;
    private Long empNum;
    private Long deptNum;
    private String reason;
    private String startDate;
    private String endDate;
    private String ampm;
    private String userName;

    @QueryProjection
    public BoardDTO(Long id, String title, Long empNum, Long deptNum, String reason,
            String startDate,
            String endDate, String ampm, String userName) {
        this.id = id;
        this.title = title;
        this.empNum = empNum;
        this.deptNum = deptNum;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ampm = ampm;
        this.userName = userName;
    }
}
