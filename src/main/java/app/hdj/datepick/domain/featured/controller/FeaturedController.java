package app.hdj.datepick.domain.featured.controller;

import app.hdj.datepick.domain.featured.dto.FeaturedMetaDto;
import app.hdj.datepick.domain.featured.dto.response.FeaturedDetailResponseDto;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.service.FeaturedService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    // TODO: Paging 처리한 리스트 가져오는 API
    @GetMapping("")
    public List<FeaturedMetaDto> getAllFeaturedMeta() {
        return featuredService.getAllFeaturedMeta();
    }

    @GetMapping("/pinned")
    public List<FeaturedMetaDto> getAllPinnedFeaturedMeta() {
        return featuredService.getAllPinnedFeaturedMeta();
    }

    @GetMapping("/{featuredId}")
    public FeaturedDetailResponseDto getFeatured(@PathVariable Long featuredId) {
        return featuredService.getFeaturedDetail(featuredId);
    }

}
