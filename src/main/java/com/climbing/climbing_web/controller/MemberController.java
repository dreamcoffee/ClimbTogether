package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.LoginDTO;
import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; //

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
        try {
            memberService.save(memberDto);
            return "login";
        }
        catch (Exception e){
            log.info("회원 가입 중 오류 발생");
            return "signup";
        }
    }

    @PostMapping("/login")
    public String loginPost(LoginDTO loginDTO, HttpSession session, RedirectAttributes rttr){
        log.info("로그인 요청 : " + loginDTO);

        try{
            MemberDTO loginMember = memberService.login(loginDTO);

            session.setAttribute("loginId", loginMember.getMember_id());
            session.setAttribute("loginUser", loginMember);

            log.info("세션 등록 완료");

            return "redirect:/";
        }
        catch (Exception e){
            log.info("로그인 중 오류 발생");
            rttr.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에 저장된 모든 정보 삭제
        session.invalidate();
        log.info("로그아웃");
        return "redirect:/";
    }
}
