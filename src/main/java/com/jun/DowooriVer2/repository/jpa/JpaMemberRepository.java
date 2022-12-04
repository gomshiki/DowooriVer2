package com.jun.DowooriVer2.repository.jpa;

import com.jun.DowooriVer2.domain.Member;
import com.jun.DowooriVer2.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j //로그 남김
@Repository
@Transactional //JPA모든 데이터 변경은 트랜잭션 안에서 이루어짐. 항상 Transactional이 들어가야함
public class JpaMemberRepository implements MemberRepository {

    // 의존관계 주입 : 이게바로 JPA
    // 저장 조회기능을 해줌
    // 스프링 부트가 em을 만들어줌
    private final EntityManager em;



    // EntityManager 생성자
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 영구히 보존한다
        return member;
    }

    @Override
    public Optional<Member> existsByUserEmail(String userEmail) {
        // em.find : pk만 조회가능
        Member member = new Member();


        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                .setParameter("email", userEmail);

        List<Member> result = query.getResultList();
//        System.out.println("result.stream() = " + result.stream());
//        System.out.println("result.stream().findAny() = " + result.stream().findAny());

        if(result.size() != 0){
            member.setEmail(result.get(0).getEmail());
        }
        return Optional.of(member);
    }

}
