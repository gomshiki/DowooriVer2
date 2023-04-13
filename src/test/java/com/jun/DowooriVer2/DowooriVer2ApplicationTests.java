package com.jun.DowooriVer2;

import com.jun.DowooriVer2.DTO.ChartDTO;
import com.jun.DowooriVer2.domain.Board;
import com.jun.DowooriVer2.domain.Department;
import com.jun.DowooriVer2.domain.Member;
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

	@Autowired
	MemberService memberService;

	@Test
	void chartTest01() {

		Member member = new Member(1L, "gomshiki@gmail.com", "1234", "김준성", 300L, "사원", "사원");


		Long empNum = member.getEmpNum();
		Long deptNum = member.getDeptNum();

		List<ChartDTO> chartDTOS = boardService.dayoffCnt(empNum, deptNum);

		System.out.println("chartDTOS = " + chartDTOS.toString());
	}

}
