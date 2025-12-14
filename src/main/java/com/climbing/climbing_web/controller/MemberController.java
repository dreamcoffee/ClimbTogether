package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.dto.LoginDTO;
import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.service.BoardService;
import com.climbing.climbing_web.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; //

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final BoardService boardService;

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    // 회원 가입 페이지 이동
    @GetMapping("/signup")
    public String signupPage(Model model){
        model.addAttribute("memberDTO", new MemberDTO());
        return "signup";
    }

    // 마이페이지 이동
    @GetMapping("/mypage")
    public String myPage(Model model, HttpSession session){
        String loginId = (String)session.getAttribute("loginId");

        if(loginId == null){
            log.warn("로그인 하지 않은 유저의 마이페이지 접근");
            return "redirect:login";
        }

        List<BoardDTO> myPostsList = boardService.getMyPosts(loginId);
        model.addAttribute("myPostsList", myPostsList);

        return "mypage";
    }

    // 회원가입 요청
    @PostMapping("/signup")
    public String signupPost(@Valid MemberDTO memberDto, BindingResult result){
        log.info("회원가입 요청 : " +memberDto);
        try {
            memberService.save(memberDto);
            return "login";
        }
        catch (Exception e){
            log.info("회원 가입 중 오류 발생" + e.getMessage());

            result.rejectValue("member_id", "duplicateId", e.getMessage());
            return "signup";
        }
    }

    // 로그인 요청
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

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에 저장된 모든 정보 삭제
        session.invalidate();
        log.info("로그아웃");
        return "redirect:/";
    }
}
