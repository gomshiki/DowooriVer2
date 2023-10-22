package com.jun.DowooriVer2.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ChartDTO {


    private String months;

    private Long totalCnt;

    @QueryProjection
    public ChartDTO(String months, Long totalCnt) {
        this.months = months;
        this.totalCnt = totalCnt;
    }

}
