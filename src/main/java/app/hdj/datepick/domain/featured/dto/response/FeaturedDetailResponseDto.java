package app.hdj.datepick.domain.featured.dto.response;

import app.hdj.datepick.domain.featured.dto.FeaturedCourseDto;
import app.hdj.datepick.domain.featured.dto.FeaturedDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeaturedDetailResponseDto {
    private FeaturedDetailDto featuredDetail;
    private List<FeaturedCourseDto> courseMetas;
}
