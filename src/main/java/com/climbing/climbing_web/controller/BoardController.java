package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardservice;

    // 커뮤니티 페이지로 이동
    @GetMapping("/community")
    public String getlist(Model model){
        List<BoardDTO> boardDTOList = boardservice.getList();
        model.addAttribute("boardList", boardDTOList);
        return "community";
    }

    @GetMapping("postId/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        BoardDTO boardDTO = boardservice.detail(id);
        model.addAttribute("postDetail", boardDTO);
        return "detailPost";
    }

    // 글 쓰기 페이지로 이동
    @GetMapping("/addPost")
    public String addPost(HttpSession session){
        if (session.getAttribute("loginId") == null){
            log.warn("로그인 하지 않은 사용자 접근 시도");
            return "redirect:/login";
        }

        return "addPost";
    }

    // 글 쓰기 요청
    @PostMapping("/addPost")
    public String addPost(BoardDTO boardDTO, HttpSession session){
        String loginId = (String) session.getAttribute("loginId");
        if (loginId == null){
            log.warn("미로그인 사용자의 로그인 시도 차단");
            return "redirect:/login";
        }

        boardDTO.setMember_id(loginId);
        boardservice.savePost(boardDTO);
        log.info("글 쓰기 요청 완료");
        return "redirect:/community";
    }
}
