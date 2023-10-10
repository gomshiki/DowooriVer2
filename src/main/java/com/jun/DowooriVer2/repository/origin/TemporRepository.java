//package com.jun.DowooriVer2.repository;
//
//import com.jun.DowooriVer2.domain.Member;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Slf4j
//@Repository
//public class TemporRepository implements MemberRepository {
//
//    private static Map<Long, Member> store = new ConcurrentHashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public Member save(Member member) {
//
//        member.setEmpNum(++sequence);
//
//        log.info("save: member={}", member);
//        store.put(member.getEmpNum(), member);
//
//        return member;
//    }
//
//    @Override
//    public Member findById(Long empNum) {
//        return store.get(empNum);
//    }
//
//    @Override
//    public void deleteMember() {
//        sequence=0L;
//        store.clear();
//    }
//
//
//}
