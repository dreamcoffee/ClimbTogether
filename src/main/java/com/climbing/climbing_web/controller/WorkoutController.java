package com.climbing.climbing_web.controller;

import com.climbing.climbing_web.dto.WorkoutDTO;
import com.climbing.climbing_web.dto.WorkoutRequestListDTO;
import com.climbing.climbing_web.service.WorkoutService;
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
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping("/workout")
    public String workout(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("loginId");
        if(session.getAttribute("loginId") == null){
            return "redirect:login";
        }

        List<WorkoutDTO> myWorkouts = workoutService.getRecordsByMemberId(loginId);

        model.addAttribute("myWorkouts", myWorkouts);

        model.addAttribute("workoutRequest", new WorkoutRequestListDTO());
        return "workout";
    }

    @GetMapping("/addworkout")
    public String addWorkout(HttpSession session, Model model) {
        return "addworkout";
    }

    @PostMapping("/addworkout")
    public String addWorkoutPost(WorkoutRequestListDTO workoutRequestListDTO, HttpSession session) {
        String memberId = (String) session.getAttribute("loginId");
        if(session.getAttribute("loginId") == null){
            log.warn("미로그인 유저의 운동 기록 Post 요청 차단");
            return "redirect:login";
        }

        List<WorkoutDTO> workoutList = workoutRequestListDTO.getRecords();

        if(workoutList != null){
            workoutService.recordClimb(workoutList, memberId);
            log.info("{} 님의 클라이밍 기록 {}건 저장 완료", memberId, workoutList.size());
        }

        return "redirect:workout";
    }
}
