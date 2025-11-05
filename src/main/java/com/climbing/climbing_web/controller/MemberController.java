package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model){
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model){
        return "signup";
    }

    @PostMapping("/signup")
    public String save(MemberDTO member){
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + member);
        memberService.save(member);

        return "redirect:/login";
    }
}
