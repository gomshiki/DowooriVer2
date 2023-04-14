package com.jun.DowooriVer2.DTO;


import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ChartDTO {


    private String months;

    private Long totalCnt;

    public ChartDTO(String months, Long totalCnt) {
        this.months = months;
        this.totalCnt = totalCnt;
    }

}
