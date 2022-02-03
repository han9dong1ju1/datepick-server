package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedCourseDto;
import app.hdj.datepick.domain.featured.entity.Featured;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeaturedCustomRepository {
    List<Long> findFeaturedIdsByIsPinnedAndCourseId(Boolean isPinned, Long courseId);
    Page<Featured> findFeaturedByIds(List<Long> featuredIds, Pageable pageable);

}
