package app.hdj.datepick.domain.featured.dto;

import app.hdj.datepick.domain.course.dto.CourseInfo;
import app.hdj.datepick.domain.featured.entity.Featured;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedDetail {

    private Featured featured;
    private List<CourseInfo> courseInfos;

}
