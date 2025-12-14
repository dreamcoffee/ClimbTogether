package com.climbing.climbing_web.repository;

import com.climbing.climbing_web.dto.WorkoutDTO;
import com.climbing.climbing_web.entity.WorkoutEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class WorkoutRepository {
    private final SqlSessionTemplate sql;

    // 운동 기록 저장
    public void save(WorkoutEntity workoutEntity) {
        log.info("운동 기록 데이터베이스 저장 요청: {}", workoutEntity);
        sql.insert("Workout.save", workoutEntity);
    }

    // 운동 기록 불러오기
    public List<WorkoutEntity> getWorkoutList(String memberId) {
        return sql.selectList("Workout.getWorkoutList", memberId);
    }

    // 특정 운동 기록 조회
    public WorkoutDTO getRecordById(int workoutId) {
        return sql.selectOne("Workout.getRecordById", workoutId);
    }

    // 운동 기록 삭제
    public void deleteRecord(int workoutId) {
        sql.delete("Workout.deleteRecord", workoutId);
    }
}
