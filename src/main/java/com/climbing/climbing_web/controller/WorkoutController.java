package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.WorkoutRequestListDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WorkoutController {

    @GetMapping("/workout")
    public String workout(HttpSession session, Model model) {
        if(session.getAttribute("loginId") == null){
            return "redirect:login";
        }

        model.addAttribute("workoutRequest", new WorkoutRequestListDTO());
        return "workout";
    }


}
