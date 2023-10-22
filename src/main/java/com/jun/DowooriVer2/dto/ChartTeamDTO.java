package com.jun.DowooriVer2.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ChartTeamDTO {


    private String userName;


    private Long totalCnt;

    @QueryProjection
    public ChartTeamDTO(String userName, Long totalCnt) {
        this.userName = userName;
        this.totalCnt = totalCnt;
    }

}
