package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedCourseDto;

import java.util.List;

public interface FeaturedCustomRepository {

    List<FeaturedCourseDto> findCourseMetaById(Long id);

}
