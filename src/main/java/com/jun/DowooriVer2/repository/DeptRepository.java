package com.jun.DowooriVer2.repository;


import com.jun.DowooriVer2.domain.Department;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DeptRepository {
    @Transactional(readOnly = true)
    List<Department> selectAllDept();

}
