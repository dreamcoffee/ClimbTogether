package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    // 글 상세 보기 페이지
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

    @GetMapping("/goUpdate/{id}")
    public String goUpdate(HttpSession session, @PathVariable("id") Integer id, Model model){
        BoardDTO post = boardservice.detail(id);
        String loginId = (String) session.getAttribute("loginId");
        if (loginId == null || !loginId.equals(post.getMember_id())){
            log.warn("수정 권한이 없는 사용자 접근 시도. 게시글 ID: {}", id);
            return "redirect:/postId/" + id;
        }

        model.addAttribute("postDetail", post);
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
        BoardDTO existingPost = boardservice.detail(boardDTO.getPostid());
        String loginId = (String) session.getAttribute("loginId");

        if (loginId == null || !loginId.equals(existingPost.getMember_id())) {
            log.warn("수정 권한 없는 사용자의 POST 요청 차단. 게시글 ID: {}", boardDTO.getPostid());
            return "redirect:/postId/" + boardDTO.getPostid();
        }

        // member_id는 변경하지 않고 기존 값을 유지
        boardDTO.setMember_id(loginId);
        boardservice.updatePost(boardDTO);

        return "redirect:/postId/" + boardDTO.getPostid(); // 수정 후 상세 페이지로 이동
    }

    // 글 삭제
    @GetMapping("/goDelete/{id}")
    public String deletePost(@PathVariable("id") Integer id, HttpSession session) {
        BoardDTO post = boardservice.detail(id);
        String loginId = (String) session.getAttribute("loginId");

        if (loginId == null || !loginId.equals(post.getMember_id())) {
            log.warn("삭제 권한 없는 사용자 접근 시도. 게시글 ID: {}", id);
            return "redirect:/postId/" + id;
        }

        boardservice.deletePost(id);
        return "redirect:/community";
    }
}
