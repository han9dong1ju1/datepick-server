package app.hdj.datepick.domain.course.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseModifyRequsetDto {

    private ModifyCourseDto course;
    private List<ModifyCoursePlaceRelationDto> placeRelations;

}
