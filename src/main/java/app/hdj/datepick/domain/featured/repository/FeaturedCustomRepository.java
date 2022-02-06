package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedCourseDto;
import app.hdj.datepick.domain.featured.dto.FeaturedPage;
import app.hdj.datepick.domain.featured.dto.FeaturedPagingParam;
import app.hdj.datepick.domain.featured.entity.Featured;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeaturedCustomRepository {
    FeaturedPage findFeaturedPageByIsPinnedAndCourseId(Boolean isPinned, Long courseId, FeaturedPagingParam featuredPagingParam);
    FeaturedPage findFeaturedPageByIsPinned(Boolean isPinned, FeaturedPagingParam featuredPagingParam);

}
