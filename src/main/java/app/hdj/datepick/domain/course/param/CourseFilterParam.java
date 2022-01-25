package app.hdj.datepick.domain.course.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CourseFilterParam {

    private List<String> tags;

}
