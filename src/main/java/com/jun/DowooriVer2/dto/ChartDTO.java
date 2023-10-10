package com.jun.DowooriVer2.dto;


import lombok.Data;

@Data
public class ChartDTO {


    private String months;

    private Long totalCnt;

    public ChartDTO(String months, Long totalCnt) {
        this.months = months;
        this.totalCnt = totalCnt;
    }

}
