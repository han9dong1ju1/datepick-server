package app.hdj.datepick.domain.course.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class CourseModifyRequsetDto {

    private ModifyCourseDto course;
    private List<ModifyCoursePlaceRelationDto> placeRelations;

}
