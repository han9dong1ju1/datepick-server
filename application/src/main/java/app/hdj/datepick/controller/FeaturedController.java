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

    // -- api/v1/featured/meta --
    @GetMapping("/meta/all")
    public List<FeaturedMeta> getAllFeatured(){
        System.out.println("Get All Featured");
        return featuredService.findAll();
    }
    @GetMapping("/meta/{featured_id}")
    public FeaturedMeta getFeaturedById(@PathVariable Long featured_id){
        System.out.println(String.format("Get Featured %d", featured_id));
        return featuredService.findById(featured_id);
    }
    //--
    //-- api/v1/featured/detail --
    @GetMapping("/detail/{featured_id}")
    public FeaturedDetail getFeaturedByIdWithDetail(@PathVariable Long featured_id){
        System.out.println(String.format("Get Featured Detail %d", featured_id));
        return featuredService.findByIdWithDetail(featured_id);
    }
    //--
}
