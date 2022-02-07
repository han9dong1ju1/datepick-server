package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.dto.FeaturedPage;
import app.hdj.datepick.domain.featured.dto.FeaturedPageRequest;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.repository.FeaturedRepository;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedService {

    private final FeaturedRepository featuredRepository;

    public FeaturedPage getFeaturedPage(Boolean isPinned, Long courseId, FeaturedPageRequest featuredPageRequest) {
        if (courseId == null){
            return featuredRepository.findFeaturedPageByIsPinned(isPinned, featuredPageRequest);
        }
        return featuredRepository.findFeaturedPageByIsPinnedAndCourseId(isPinned, courseId, featuredPageRequest);
    }

    public Featured getFeatured(Long featuredId) {
        return featuredRepository.findById(featuredId).orElseThrow();
    }
}
