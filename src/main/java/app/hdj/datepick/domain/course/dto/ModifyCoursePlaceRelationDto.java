package app.hdj.datepick.domain.course.dto;

import lombok.Getter;
import lombok.ToString;

import java.sql.Time;

@ToString
@Getter
public class ModifyCoursePlaceRelationDto {
    private Byte placeOrder;
    private Time visitTime;
    private String memo;
    private Long placeId;
}
