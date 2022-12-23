package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
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

    // DB에서 로그인 정보 가져오기
    Member save(Member member);

    Member findById(Long empNum);

    void deleteMember();

}
