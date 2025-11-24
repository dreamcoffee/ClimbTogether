package com.climbing.climbing_web.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class WorkoutEntity {
    private Long id;
    private String memberId;
    private String gymName;           // 센터 이름
    private String branch;            // 지점 (예를 들어 센터 이름 : 더클라임 / 지점 : 양재, 강남 등등등등)
    private LocalDate recordDate;     // 기록 날짜

    private String difficultyColor;   // 난이도 색상 (예: Red, Blue)
    private Integer solvedCount;      // 완등 개수
}
