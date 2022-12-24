package com.jun.DowooriVer2.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name = "Board_table")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Board_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "emp_num")
    private String empNum;

    @Column(name = "dept_num")
    private String deptNum;

    @Column(name = "reason")
    private String reason;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name="ampm")
    private String ampm;


}
