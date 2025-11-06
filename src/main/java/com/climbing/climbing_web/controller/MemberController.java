package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(MemberDTO memberDto){
        log.info("회원가입 요청 : " +memberDto);
        return "signup";
    }
}
