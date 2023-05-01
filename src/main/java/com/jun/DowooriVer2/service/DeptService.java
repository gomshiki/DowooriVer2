package com.jun.DowooriVer2.service;

import com.jun.DowooriVer2.domain.Department;
import com.jun.DowooriVer2.repository.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class DeptService {

    private DeptRepository deptRepository;

    public DeptService(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    public List<Department> selectAllDept() {
        return deptRepository.selectAllDept();
    };
}
