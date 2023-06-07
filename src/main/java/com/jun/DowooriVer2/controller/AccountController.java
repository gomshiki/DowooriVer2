package com.jun.DowooriVer2.controller;

import com.jun.DowooriVer2.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AccountController {

   //  회원가입 시 데이터를 담을 객체(model) 전달)
    @GetMapping("/register")
    public String register(Model model, Account account) {
        model.addAttribute("account", account);

        return "userRegister";
    }


}
