package app.hdj.datepick.controller;

import app.hdj.datepick.domain.entity.Featured;
import app.hdj.datepick.domain.entity.FeaturedMeta;
import app.hdj.datepick.domain.service.FeaturedService;
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
    public List<FeaturedMeta> getAllFeatured(){
        return featuredService.getAllFeaturedMetas();
    }

    @GetMapping("/{featuredId}")
    public Featured getFeaturedByIdWithDetail(@PathVariable Long featuredId){
        return featuredService.getFeatured(featuredId);
    }

}
