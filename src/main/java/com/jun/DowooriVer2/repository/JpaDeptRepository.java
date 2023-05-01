package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.domain.Department;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class JpaDeptRepository implements DeptRepository{


    private final EntityManager em;


    @Override
    public List<Department> selectAllDept() {

        String sql = "select d from Department d where d.useYn = 'y'";

        List<Department> resultList = em.createQuery(sql, Department.class).getResultList();


        log.info("resultList >> " + resultList.toString());

        return resultList;
    }


}
