package com.climbing.climbing_web.repository;

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

    public void save(WorkoutEntity workoutEntity) {
        log.info("운동 기록 데이터베이스 저장 요청: {}", workoutEntity);
        sql.insert("Workout.save", workoutEntity);
    }

    public List<WorkoutEntity> getWorkoutList(String memberId) {
        return sql.selectList("Workout.getWorkoutList", memberId);
    }
}
