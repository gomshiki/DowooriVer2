//package com.jun.DowooriVer2;
//
//
//import com.jun.DowooriVer2.repository.MemberRepository;
//import com.jun.DowooriVer2.repository.JpaMemberRepository;
//import com.jun.DowooriVer2.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Configuration
//public class SpringConfig {
//
//
//    private final MemberRepository memberRepository;
//
//    public SpringConfig(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    // JPA에서 사용하기위해 필수 설정해줌(이것때문에 계속 실행 안됐음...)
//
//    public MemberService memberService(){
//        return new MemberService(memberRepository);
//    }
//
//
//}
