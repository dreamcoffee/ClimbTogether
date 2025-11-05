package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
}
