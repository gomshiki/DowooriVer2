package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.dto.HomeDTO;
import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
public class MemberService {


    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findById(Long empNum) {
        return memberRepository.findById(empNum);
    }

    public List<HomeDTO> findAll() {
        return memberRepository.findAll();
    }

//    public List<Member> save(){
//
//        List<Member> members = new ArrayList<>();
//
//        return members;
//    }

//    public void deleteMember(){
//        memberRepository.deleteMember();
//    }



//    // 회원가입
//    @Transactional
//    public Member join(Member member) {
//        memberRepository.save(member);
//        return member;
//    }

//    public Optional<Member> existsByUserEmail(String userEmail){
//        return memberRepository.existsByUserEmail1(userEmail);
//    }

}
