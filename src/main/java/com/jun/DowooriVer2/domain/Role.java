package com.jun.DowooriVer2.domain;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "role_table")
public class Role {

    @Id @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DBMS의 INCREMENT AUTO 사용할 경우
    private Long id;

    @Column(name = "role_name")
    private String roleName;

}
