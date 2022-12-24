package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; //로그기록용
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j //로그 남김
@Repository
@RequiredArgsConstructor
@Transactional //JPA모든 데이터 변경은 트랜잭션 안에서 이루어짐. 항상 Transactional이 들어가야함
public class JpaMemberRepository implements MemberRepository {

    // 의존관계 주입 : 이게바로 JPA
    // 저장 조회기능을 해줌
    // 스프링 부트가 em을 만들어줌
    private final EntityManager em;

// EntityManager 생성자 : JPQL을 사용할 때 사용함.



    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // 회원가입
//    @Override
//    public Member save(Member member) {
//        em.persist(member); // 영구히 보존한다
//        return member;
//    }


    // 이메일 중복확인
    // 방법 1 : select문으로 조회 시 조회결과가 있으면 해당 객체의 이메일 리턴, 없으면 빈 객체 반환
//    @Override
//    public Optional<Member> existsByUserEmail1(String userEmail) {
//        // em.find : pk만 조회가능
//        Member member = new Member();
//
//        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
//                .setParameter("email", userEmail);
//
//        List<Member> result = query.getResultList();
//
//        if(result.size() != 0){
//            member.setEmail(result.get(0).getEmail());
//        }
//        return Optional.of(member);
//    }

    // 이메일 중복확인
    // 방법 2 : count문을 이용해 아이디 조회 후 정수형으로 값을 반환
//    @Override
//    public Long exitsByUserEmail2(String userEmail) {
//        TypedQuery<Member> query = em.createQuery("SELECT COUNT(*) FROM Member m  WHERE m.email = :email", Member.class);
//        TypedQuery<Member> email = query.setParameter("email", userEmail);
//        return 0L;
//    }




}
