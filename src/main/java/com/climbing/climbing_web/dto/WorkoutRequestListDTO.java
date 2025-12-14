package com.climbing.climbing_web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class WorkoutRequestListDTO {
    // HTML 폼에서 records[0].difficultyColor, records[1].solvedCount 형태로 데이터를 받는다.
    // 일반적인 HTML의 폼의 경우 단일 값만 보내는데 여러개의 운동 기록을 한번에 전송하기 위해서는 배열 형태로 전송해야 한다.
    private List<WorkoutDTO> records;
}