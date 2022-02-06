package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedPage;
import app.hdj.datepick.domain.featured.dto.FeaturedPagingRequest;

public interface FeaturedCustomRepository {
    FeaturedPage findFeaturedPageByIsPinnedAndCourseId(Boolean isPinned, Long courseId, FeaturedPagingRequest featuredPagingRequest);
    FeaturedPage findFeaturedPageByIsPinned(Boolean isPinned, FeaturedPagingRequest featuredPagingRequest);
}
