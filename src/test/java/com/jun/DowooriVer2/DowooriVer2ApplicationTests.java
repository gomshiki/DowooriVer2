package com.jun.DowooriVer2;

import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.domain.Pagination;
import com.jun.DowooriVer2.service.BoardService;
import com.jun.DowooriVer2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
@Controller
class DowooriVer2ApplicationTests {
	@Autowired
	BoardService boardService;

	@Test
	void contextLoads() {


		Long empNum = 4L;

		int totalListCnt = 30;
		int page = 1;

		Pagination pagination = new Pagination(totalListCnt, page);

		// DB select start index
		int startIndex = pagination.getStartIndex();

		// 페이지 당 보여지는 게시글의 최대 개수
		int pageSize = pagination.getPageSize();

		List<Board> boards = boardService.findAllPaging(empNum, startIndex, pageSize);

		page = 3;

		pagination = new Pagination(totalListCnt, page);

		// DB select start index
		startIndex = pagination.getStartIndex();

		// 페이지 당 보여지는 게시글의 최대 개수
		pageSize = pagination.getPageSize();

		boards = boardService.findAllPaging(empNum, startIndex, pageSize);





	}

}
