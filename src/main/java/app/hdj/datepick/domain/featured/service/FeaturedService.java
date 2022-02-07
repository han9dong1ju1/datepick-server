package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.dto.FeaturedPage;
import app.hdj.datepick.domain.featured.dto.FeaturedPageRequest;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.repository.FeaturedRepository;
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
        return featuredRepository.findFeaturedById(featuredId);
    }
//    public List<FeaturedMetaDto> getFeaturedList() {
//        return featuredRepository.findAllBy(FeaturedMetaDto.class);
//    }
//
//    public List<FeaturedMetaDto> getPinnedFeaturedList() {
//        return featuredRepository.findAllByIsPinnedTrue(FeaturedMetaDto.class);
//    }
//
//    // TODO: 파라미터 선정 및 구현
//    public void getFeaturedPage() {}
//
//    public FeaturedDetailResponseDto getFeatured(Long featuredId) {
//        FeaturedDetailDto featuredDetailDto
//                = featuredRepository.findById(featuredId, FeaturedDetailDto.class).orElseThrow(RuntimeException::new);
//        List<FeaturedCourseDto> featuredCourseMetaDtos
//                = featuredRepository.findCourseInFeaturedById(featuredId);
//        return new FeaturedDetailResponseDto(featuredDetailDto, featuredCourseMetaDtos);
//    }

}
