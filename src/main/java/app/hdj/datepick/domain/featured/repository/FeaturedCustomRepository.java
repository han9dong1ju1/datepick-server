package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedCourseMetaDto;

import java.util.List;

public interface FeaturedCustomRepository {

    List<FeaturedCourseMetaDto> findCourseMetaById(Long id);

}
