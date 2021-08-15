package app.hdj.datepick.controller;


import app.hdj.datepick.domain.model.FeaturedDetail;
import app.hdj.datepick.domain.model.FeaturedMeta;
import app.hdj.datepick.domain.service.FeaturedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    @Autowired
    public FeaturedController(FeaturedService featuredService) {
        this.featuredService = featuredService;
    }


    @GetMapping("")
    public List<FeaturedMeta> getAllFeatured(){
        return featuredService.getAllFeaturedMetas();
    }

    @GetMapping("/{featuredId}")
    public FeaturedDetail getFeaturedByIdWithDetail(@PathVariable Long featuredId){
        return featuredService.getFeaturedDetail(featuredId);
    }

}
