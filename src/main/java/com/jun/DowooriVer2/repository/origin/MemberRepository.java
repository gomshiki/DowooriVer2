package com.jun.DowooriVer2.repository.origin;

import com.jun.DowooriVer2.dto.HomeDTO;
import com.jun.DowooriVer2.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    // 회원저장
//    Member save(Member member);
//
//    // 이메일 중복 확인 방법1
//    Optional<Member> existsByUserEmail1(String emailId);
//
//    // 이메일 중복 확인 방법2
//    Long exitsByUserEmail2(String emailId);
//
//    Member findByLoginEmail(String email);

    // DB에서 회원가입
   // Member save(Member member);

    // 회원정보가져오기
    Optional<Member> findById(Long empNum);

    List<HomeDTO> findAll();

    // 회원삭제
//    void deleteMember();

}
