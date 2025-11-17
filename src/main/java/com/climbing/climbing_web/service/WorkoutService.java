package com.climbing.climbing_web.service;

import com.climbing.climbing_web.dto.WorkoutDTO;
import com.climbing.climbing_web.entity.WorkoutEntity;
import com.climbing.climbing_web.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public void recordClimb(List<WorkoutDTO> workoutList, String memberId) {
        WorkoutDTO commonData = workoutList.get(0);
        String commonBranch = commonData.getBranch();
        LocalDate commonDate = commonData.getRecordDate();

        for(WorkoutDTO workoutDTO : workoutList) {
           if(workoutDTO.getSolvedCount() == null || workoutDTO.getSolvedCount() <= 0) {
                continue;
           }

           workoutDTO.setMemberId(memberId);
           workoutDTO.setGymName("더클라임");

           if(workoutDTO.getBranch() == null){
               workoutDTO.setBranch(commonBranch);
           }
           if(workoutDTO.getRecordDate() == null){
               workoutDTO.setRecordDate(commonDate);
           }

           WorkoutEntity workoutEntity = convertToEntity(workoutDTO);
           workoutRepository.save(workoutEntity);
        }
    }

    public List<WorkoutDTO> getRecordsByMemberId(String memberId) {
        List<WorkoutEntity> entities = workoutRepository.getWorkoutList(memberId);
        // Entity 리스트를 DTO 리스트로 변환
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private WorkoutDTO convertToDTO(WorkoutEntity workoutEntity) {
        WorkoutDTO workoutDTO = new WorkoutDTO();
        workoutDTO.setId(workoutEntity.getId());
        workoutDTO.setMemberId(workoutEntity.getMemberId());
        workoutDTO.setGymName(workoutEntity.getGymName());
        workoutDTO.setBranch(workoutEntity.getBranch());
        workoutDTO.setRecordDate(workoutEntity.getRecordDate());
        workoutDTO.setDifficultyColor(workoutEntity.getDifficultyColor());
        workoutDTO.setSolvedCount(workoutEntity.getSolvedCount());
        return workoutDTO;
    }

    private WorkoutEntity convertToEntity(WorkoutDTO dto) {
        WorkoutEntity entity = new WorkoutEntity();
        entity.setMemberId(dto.getMemberId());
        entity.setGymName(dto.getGymName());
        entity.setBranch(dto.getBranch());
        // 날짜가 없으면 현재 날짜로 설정
        entity.setRecordDate(dto.getRecordDate() != null ? dto.getRecordDate() : LocalDate.now());
        entity.setDifficultyColor(dto.getDifficultyColor());
        entity.setSolvedCount(dto.getSolvedCount());
        return entity;
    }
}
