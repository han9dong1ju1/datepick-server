package app.hdj.datepick.domain.featured.controller;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.service.FeaturedService;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    @GetMapping("")
    public CustomPage<Featured> getFeaturedPage(@RequestParam(value = "is_pinned", required = false) Boolean isPinned,
                                                    @RequestParam(value = "course_id", required = false) Long courseId,
                                                    @Valid PagingParam pagingParam) {
        return featuredService.getFeaturedPage(isPinned, courseId, pagingParam);
    }

    @GetMapping("/{featuredId}")
    public Featured getFeatured(@PathVariable Long featuredId) {
        return featuredService.getFeatured(featuredId);
    }

}
