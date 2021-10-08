package app.hdj.datepick.domain.course.dto.request;

import app.hdj.datepick.domain.course.dto.CoursePlaceRelationDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseModifyRequsetDto {

    private ModifyCourseDto course;
    private List<ModifyCoursePlaceRelationDto> placeRelations;

}
