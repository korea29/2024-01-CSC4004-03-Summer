package com.dgu_csc.akomanager_back.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // 회원가입 페이지
    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }
}