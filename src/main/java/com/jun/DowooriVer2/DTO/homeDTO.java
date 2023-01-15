package com.jun.DowooriVer2.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class homeDTO {

    // join 시 순서가 맞아야함... ㅠㅠ
    private String userName;

    private Long empNum;
    private String email;


    private String position;
    private String deptName;

    // join 작성 시 생성자 parameter 순서도 쿼리문과 동일해야함!
    public homeDTO(String userName, Long empNum, String email,  String position, String deptName) {
        this.empNum = empNum;
        this.email = email;
        this.userName = userName;
        this.position = position;
        this.deptName = deptName;
    }
}
