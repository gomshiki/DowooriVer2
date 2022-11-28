package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.repository.MemberRepository;
import com.jun.DowooriVer2.repository.jpa.JpaMemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
public class MemberService {


    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Member join(Member member) {
        memberRepository.save(member);
        return member;
    }

}
