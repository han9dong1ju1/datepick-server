package app.hdj.datepick.controller;


import app.hdj.datepick.domain.dto.FeaturedDetail;
import app.hdj.datepick.domain.dto.FeaturedMeta;
import app.hdj.datepick.domain.service.FeaturedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
        System.out.println("Get All Featured");
        return featuredService.findAll();
    }
    @GetMapping("/{featuredId}")
    public FeaturedDetail getFeaturedByIdWithDetail(@PathVariable Long featuredId){
        System.out.println(String.format("Get Featured Detail %d", featuredId));
        return featuredService.findByIdWithDetail(featuredId);
    }
}
