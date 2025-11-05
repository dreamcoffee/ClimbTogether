package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;

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

    @PostMapping("/login") // login.html의 form action과 일치
    public String login(MemberDTO memberDTO, HttpSession session){

        MemberDTO loginMember = memberService.login(memberDTO);

        if (loginMember != null) {
            // 로그인 성공 시
            session.setAttribute("loginMember", loginMember); // 세션에 DTO 객체 저장
            return "redirect:/"; // 메인 페이지로 리다이렉트
        } else {
            // 로그인 실패 시 (여기서 실패 메시지를 Model에 담아 전달하는 로직을 추가할 수 있음)
            return "login"; // 로그인 페이지로 돌아가기
        }
    }

    @PostMapping("/signup")
    public String save(MemberDTO member){
        memberService.save(member);
        return "redirect:/login";
    }
}
