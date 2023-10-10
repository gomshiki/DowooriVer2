package com.jun.DowooriVer2;

import com.jun.DowooriVer2.service.BoardService;
import com.jun.DowooriVer2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;

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

	}

}
