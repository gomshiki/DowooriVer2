package com.jun.DowooriVer2.dto;

import lombok.Data;

@Data
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

}
