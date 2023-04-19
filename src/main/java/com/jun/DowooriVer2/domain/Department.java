package com.jun.DowooriVer2.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name = "dept_table")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_num")
    private Long id;

    @Column
    private String deptName;

    @Column(name = "use_yn")
    private String useYn;
}
