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
    private List<WorkoutDTO> records;
}