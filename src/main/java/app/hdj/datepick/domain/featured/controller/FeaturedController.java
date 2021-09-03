package app.hdj.datepick.domain.featured.controller;

import app.hdj.datepick.domain.featured.dto.FeaturedDetail;
import app.hdj.datepick.domain.featured.dto.FeaturedMeta;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.service.FeaturedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    @GetMapping("")
    public List<Featured> getAllFeatrued(){
        return featuredService.getAllFeatured();
    }

    @GetMapping("/{featuredId}")
    public FeaturedDetail getFeaturedDetail(@PathVariable Long featuredId){
        return featuredService.getFeaturedDetail(featuredId);
    }

}
