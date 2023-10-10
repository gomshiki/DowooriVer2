package com.jun.DowooriVer2.dto;


import lombok.Data;

@Data
public class ChartTeamDTO {


    private String userName;


    private Long totalCnt;

    public ChartTeamDTO(String userName, Long totalCnt) {
        this.userName = userName;
        this.totalCnt = totalCnt;
    }

}
