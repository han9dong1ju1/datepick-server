package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.dto.FeaturedCourseDto;
import app.hdj.datepick.domain.featured.dto.FeaturedDetailDto;
import app.hdj.datepick.domain.featured.dto.FeaturedMetaDto;
import app.hdj.datepick.domain.featured.dto.response.FeaturedDetailResponseDto;
import app.hdj.datepick.domain.featured.exception.FeaturedNotFoundException;
import app.hdj.datepick.domain.featured.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedService {

    private final FeaturedRepository featuredRepository;

    // TODO: 삭제
    public List<FeaturedMetaDto> getFeaturedList() {
        return featuredRepository.findAllBy(FeaturedMetaDto.class);
    }

    public List<FeaturedMetaDto> getPinnedFeaturedList() {
        return featuredRepository.findAllByIsPinnedTrue(FeaturedMetaDto.class);
    }

    // TODO: 파라미터 선정 및 구현
    public void getFeaturedPage() {}

    public FeaturedDetailResponseDto getFeatured(Long featuredId) {
        FeaturedDetailDto featuredDetailDto
                = featuredRepository.findById(featuredId, FeaturedDetailDto.class).orElseThrow(FeaturedNotFoundException::new);
        List<FeaturedCourseDto> featuredCourseMetaDtos
                = featuredRepository.findCourseInFeaturedById(featuredId);
        return new FeaturedDetailResponseDto(featuredDetailDto, featuredCourseMetaDtos);
    }

}
