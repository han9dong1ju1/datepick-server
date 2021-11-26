package app.hdj.datepick.domain.course.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModifyCoursePlaceRelationDto {
    private Byte placeOrder;
    private Time visitTime;
    private String memo;
    private Long placeId;
}
