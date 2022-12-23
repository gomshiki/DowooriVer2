package com.jun.DowooriVer2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


// data :getter, setter, constructer 등 한꺼번에 생성해주는 어노테이
// JPA에서는 @entity를 설정해줘야함
@Entity
@Data
@Table(name = "emp_table")
public class Member {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DBMS의 INCREMENT AUTO 사용할 경우
    private Long empNum;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email
    @Column(name = "email", unique = true) // unique 설정으로 에러발생할 것
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;
    @NotBlank(message = "사용자이름은 필수 입력값입니다.")
    @Column(name = "user_name")
    private String userName;

    @Column(name = "dept_name")
    private String deptName;
    private String position;
    @Column(name = "chief_name")
    private String chiefName;

    public Member(Long empNum, String email, String password, String userName, String deptName, String position, String chiefName) {
        this.empNum = empNum;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.deptName = deptName;
        this.position = position;
        this.chiefName = chiefName;
    }


    // JPA는 'public' 또는 'protected'의 기본 생성자가 필수이다.
    public Member() {

    }



}
