package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedPage;
import app.hdj.datepick.domain.featured.dto.FeaturedPageRequest;

public interface FeaturedCustomRepository {
    FeaturedPage findFeaturedPageByIsPinnedAndCourseId(Boolean isPinned, Long courseId, FeaturedPageRequest featuredPageRequest);
    FeaturedPage findFeaturedPageByIsPinned(Boolean isPinned, FeaturedPageRequest featuredPageRequest);
}
