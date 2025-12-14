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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    // 운동 기록 페이지 이동
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

    // 운동 등록 페이지 이동
    @GetMapping("/addworkout")
    public String addWorkout(HttpSession session, Model model) {
        return "addworkout";
    }

    // 운동 기록 등록 요청
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
            log.info("{} 유저의 클라이밍 기록 {}건 저장 완료", memberId, workoutList.size());
        }

        return "redirect:workout";
    }

    @GetMapping("/deleteWorkout/{workoutId}")
    public String deleteWorkout(@PathVariable("workoutId") int workoutId, HttpSession session) {

        String loginId = (String) session.getAttribute("loginId");

        if (loginId == null) {
            log.warn("기록 삭제 권한 없는 상태 (로그인 필요)");
            return "redirect:/login";
        }

        WorkoutDTO record = workoutService.getRecordById(workoutId);

        if (record == null || !loginId.equals(record.getMemberId())) {
            log.warn("기록 삭제 권한 없음. 기록 ID: {}, 로그인 ID: {}", workoutId, loginId);
            return "redirect:/mypage\\";
        }

        workoutService.deleteRecord(workoutId);

        return "redirect:/workout";
    }
}
