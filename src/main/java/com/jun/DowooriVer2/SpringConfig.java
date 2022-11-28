package com.jun.DowooriVer2;


import com.jun.DowooriVer2.repository.MemberRepository;
import com.jun.DowooriVer2.repository.jpa.JpaMemberRepository;
import com.jun.DowooriVer2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class SpringConfig {


    @PersistenceContext // 스프링에서는 생략 가능
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    // JPA에서 사용하기위해 필수 설정해줌(이것때문에 계속 실행 안됐음...)
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

}
