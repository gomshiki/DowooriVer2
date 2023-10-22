package com.jun.DowooriVer2.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalendarDTO {

    private String title;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String userName;

    private String ampm;

    @QueryProjection
    public CalendarDTO(String title, Date startDate, Date endDate, String userName, String ampm) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userName = userName;
        this.ampm = ampm;
    }



}
