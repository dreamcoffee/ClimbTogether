package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {
    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model){
        List<BoardDTO> boardDTOList = boardService.getLatestPosts();
        model.addAttribute("latestPosts",boardDTOList);
        return "index";
    }
}
