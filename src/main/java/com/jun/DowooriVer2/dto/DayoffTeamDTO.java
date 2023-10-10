package com.jun.DowooriVer2.dto;


import lombok.Data;

@Data
public class DayoffTeamDTO {

    private String teamName;

    private Long totalCnt;

    private String months;

    public DayoffTeamDTO(String months, String teamName, Long totalCnt) {
        this.teamName = teamName;
        this.totalCnt = totalCnt;
        this.months = months;
    }
}
