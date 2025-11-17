package com.climbing.climbing_web.service;

import com.climbing.climbing_web.dto.WorkoutDTO;
import com.climbing.climbing_web.entity.WorkoutEntity;
import com.climbing.climbing_web.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public void recordClimb(List<WorkoutDTO> workoutList, String memberId) {
        for(WorkoutDTO workoutDTO : workoutList) {
           if(workoutDTO.getSolvedCount() == null || workoutDTO.getSolvedCount() <= 0) {
                continue;
           }

           workoutDTO.setMemberId(memberId);
           workoutDTO.setGymName("더클라임");

           WorkoutEntity workoutEntity = convertToEntity(workoutDTO);
           workoutRepository.save(workoutEntity);
        }
    }

    private WorkoutEntity convertToEntity(WorkoutDTO dto) {
        WorkoutEntity entity = new WorkoutEntity();
        entity.setMemberId(dto.getMemberId());
        entity.setGymName(dto.getGymName());
        // 날짜가 없으면 현재 날짜로 설정
        entity.setRecordDate(dto.getRecordDate() != null ? dto.getRecordDate() : LocalDate.now());
        entity.setDifficultyColor(dto.getDifficultyColor());
        entity.setSolvedCount(dto.getSolvedCount());
        return entity;
    }
}
