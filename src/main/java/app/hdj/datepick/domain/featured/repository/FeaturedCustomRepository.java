package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedCourseDto;
import app.hdj.datepick.domain.featured.entity.Featured;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeaturedCustomRepository {
    Page<Featured> findFeaturedByIsPinnedAndCourseId(Boolean isPinned, Long courseId, Pageable pageable);
}
