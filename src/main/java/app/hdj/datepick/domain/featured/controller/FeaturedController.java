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

    @GetMapping("")
    public Page<FeaturedMetaDto> getAllFeaturedMeta(){
        return null;
        //return featuredService.getAllFeaturedMeta();
    }

    @GetMapping("/main")
    public List<FeaturedMetaDto> getMainFeaturedMeta(){
        return featuredService.getAllFeaturedMeta();
    }

    @GetMapping("/{featuredId}")
    public FeaturedDetailResponseDto getFeaturedDetail(@PathVariable Long featuredId){
        return featuredService.getFeaturedDetail(featuredId);
    }

}
