package com.jun.DowooriVer2.repository;

import com.jun.DowooriVer2.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {

    List<Board> findAll(Long empNum);
}
