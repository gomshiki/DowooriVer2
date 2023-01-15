package com.jun.DowooriVer2.DTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class BoardDTO {

    private Long id;
    private String title;
    private String empNum;
    private Long deptNum;
    private String reason;
    private String startDate;
    private String endDate;
    private String ampm;

}
