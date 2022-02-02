package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.repository.FeaturedRepository;
import app.hdj.datepick.global.common.PagingParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedService {

    private final FeaturedRepository featuredRepository;

    public Page<Featured> getFeaturedPage(Boolean isPinned, Pageable pageable) {
        return featuredRepository.findByIsPinnedOrderByCreatedAtAsc(isPinned, pageable);
    }
    public Page<Featured> getFeaturedPage(Boolean isPinned, Long courseId, Pageable pageable) {

        return null;
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
