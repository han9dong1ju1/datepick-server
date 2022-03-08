package app.hdj.datepick.domain.featured.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class FeaturedDetailResponseDto {
    private FeaturedDetailDto featuredDetail;
    private List<FeaturedCourseDto> courseMetas;
}
