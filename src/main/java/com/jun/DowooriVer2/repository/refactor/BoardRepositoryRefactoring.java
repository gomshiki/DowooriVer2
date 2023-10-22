package com.jun.DowooriVer2.repository.refactor;

import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.repository.origin.BoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositoryRefactoring extends JpaRepository<Board, Long> {



}
