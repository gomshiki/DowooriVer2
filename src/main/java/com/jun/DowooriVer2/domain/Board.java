package com.jun.DowooriVer2.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "board_table")
@DynamicInsert // insert 시 null인 필드 제외
@DynamicUpdate // update 시 null인 필드 제외
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Board_id")
    private Long id;

    private String title;

    @Column(name = "dept_num")
    private Long deptNum;

    @Column(name = "emp_num")
    private Long empNum;

    private String reason;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;

    private String ampm;
    private String status;
    private String approveLevel;

    @ManyToOne
    @JoinColumn(name = "dept_num", updatable = false, insertable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "emp_num", updatable = false, insertable = false)
    private Member member;

    public Board() {
    }

    public Board(Long id, String title, Long deptNum, Long empNum, String reason, Date startDate, Date endDate, Date writeDate, String ampm, String status, String approveLevel, Department department, Member member) {
        this.id = id;
        this.title = title;
        this.deptNum = deptNum;
        this.empNum = empNum;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.writeDate = writeDate;
        this.ampm = ampm;
        this.status = status;
        this.approveLevel = approveLevel;
        this.department = department;
        this.member = member;
    }
}
