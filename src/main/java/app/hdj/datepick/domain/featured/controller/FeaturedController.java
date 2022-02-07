package app.hdj.datepick.domain.featured.controller;

import app.hdj.datepick.domain.featured.dto.FeaturedPage;
import app.hdj.datepick.domain.featured.dto.FeaturedPageRequest;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.service.FeaturedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    @GetMapping("")
    public FeaturedPage getFeaturedMetaList(@RequestParam(value = "is_pinned", defaultValue = "true") Boolean isPinned,
                                            @RequestParam(value = "course_id", required = false) Long courseId,
                                            @Valid FeaturedPageRequest featuredPageRequest) {
        return featuredService.getFeaturedPage(isPinned, courseId, featuredPageRequest);
    }

    @GetMapping("/{featuredId}")
    public Featured getFeaturedMetaList(@PathVariable Long featuredId) {
        return featuredService.getFeatured(featuredId);
    }

}
