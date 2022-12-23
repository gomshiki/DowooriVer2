package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
public class MemberService {


    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findById(Long empNum) {
        return memberRepository.findById(empNum);
    }

    public List<Member> save(){

        List<Member> members = new ArrayList<>();
        members.add(memberRepository.save(associate()));
        members.add(memberRepository.save(manager()));
        members.add(memberRepository.save(boss()));

        return members;
    }

    public void deleteMember(){
        memberRepository.deleteMember();
    }


    // 회원정보
    private static Member associate(){

        Member associate = new Member();
        associate.setEmail("jskim@gmail.com");
        associate.setUserName("김준성");
        associate.setPosition("사원");
        associate.setPassword("1234");
        associate.setDeptName("IT솔루션개발부");
        associate.setChiefName("김병준");

        return associate;
    }

    private static Member manager(){

        Member manager = new Member();
        manager.setEmail("bjkim@gmail.com");
        manager.setUserName("김병준");
        manager.setPosition("과장");
        manager.setPassword("qwer");
        manager.setDeptName("IT솔루션개발부");
        manager.setChiefName("이영춘");

        return manager;
    }

    private static Member boss(){

        Member boss = new Member();
        boss.setEmail("gomshiki@gmail.com");
        boss.setUserName("이영춘");
        boss.setPosition("부장");
        boss.setPassword("zxcv");
        boss.setDeptName("IT솔루션개발부");

        return boss;
    }



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
