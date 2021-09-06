package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.dto.FeaturedCourseMetaDto;
import app.hdj.datepick.domain.featured.dto.FeaturedDetailDto;
import app.hdj.datepick.domain.featured.dto.FeaturedMetaDto;
import app.hdj.datepick.domain.featured.dto.response.FeaturedDetailResponseDto;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedService {

    private final FeaturedRepository featuredRepository;

    public List<FeaturedMetaDto> getAllFeaturedMeta() {
        return featuredRepository.findAllBy(FeaturedMetaDto.class);
    }

    @Transactional
    public FeaturedDetailResponseDto getFeaturedDetail(Long id) {
        FeaturedDetailDto featuredDetailDto
                = featuredRepository.findById(id, FeaturedDetailDto.class).orElseThrow();
        List<FeaturedCourseMetaDto> featuredCourseMetaDtos
                = featuredRepository.findCourseMetaById(id);
        return new FeaturedDetailResponseDto(featuredDetailDto, featuredCourseMetaDtos);
    }
}
