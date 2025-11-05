package com.climbing.climbing_web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MemberController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }
}
