package app.hdj.datepick.domain.featured.dto;

import app.hdj.datepick.domain.course.dto.CourseFeaturedInfo;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedDetail {

    private FeaturedMeta featuredMeta;
    private String content;
    private List<CourseFeaturedInfo> featuredDetail;

}
