package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("signup")
    public String signup(Model model){
        return "signup";
    }

    @PostMapping("signup")
    public String join(Model model){
        return "index";
    }
}
