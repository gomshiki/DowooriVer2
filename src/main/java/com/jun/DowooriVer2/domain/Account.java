package com.jun.DowooriVer2.domain;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_table") @ToString(of = {"id", "userName", "email", "createTime"})
public class Account {

    @Id @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DBMS의 INCREMENT AUTO 사용할 경우
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String password;
    private String email;
    private Boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;


}
