package com.jun.DowooriVer2.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberEnrollDTO {

    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다")
    private String password;

    @NotBlank(message = "사용자 이름은 필수 입력 사항입니다.")
    private String userName;

    @NotBlank(message = "부서 이름은 필수 입력 사항입니다.")
    private String deptName;

    @NotBlank(message = "직급은 필수 입력 사항입니다.")
    private String position;

    private String spot;
}
