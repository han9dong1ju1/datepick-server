package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedService {

    private final FeaturedRepository featuredRepository;

//    // TODO: 삭제
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
