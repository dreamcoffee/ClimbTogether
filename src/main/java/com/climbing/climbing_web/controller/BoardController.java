package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardservice;

    @GetMapping("/community")
    public String getlist(Model model){
        List<BoardDTO> boardDTOList = boardservice.getList();
        model.addAttribute("boardList", boardDTOList);
        return "community";
    }

    @GetMapping("/addPost")
    public String addPost(HttpSession session){
        if (session.getAttribute("loginId") == null){
            log.warn("로그인 하지 않은 사용자 접근 시도");
            return "redirect:/login";
        }

        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(BoardDTO boardDTO){
        return "community";
    }
}
