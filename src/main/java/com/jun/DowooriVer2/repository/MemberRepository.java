package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository{

    Member save(Member member);

    Optional<Member> existsByUserEmail(String emailId);
}
