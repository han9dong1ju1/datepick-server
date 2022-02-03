package app.hdj.datepick.domain.featured.controller;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.params.FeaturedRequestParam;
import app.hdj.datepick.domain.featured.service.FeaturedService;
import app.hdj.datepick.global.common.PagingParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    @GetMapping("")
    public Page<Featured> getFeaturedMetaList(@RequestParam(value = "is_pinned") Boolean isPinned,
                                              @RequestParam(value = "course_id", required = false) Long courseId,
                                              @PageableDefault(sort="created_at", direction = Sort.Direction.DESC) Pageable pageable
                                                    ) {

        //FeaturedRequestParam requestParam = new FeaturedRequestParam(isPinned, courseId);
        if (courseId == null) {
            return featuredService.getFeaturedPage(isPinned, pageable);
        } else {
            return featuredService.getFeaturedPage(isPinned, courseId, pageable);
        }

    }
    @GetMapping("{featuredId}")
    public Featured getFeaturedMetaList(@PathVariable Long featuredId) {
        return featuredService.getFeatured(featuredId);
    }

//
//    @GetMapping("/pinned")
//    public List<FeaturedMetaDto> getPinnedFeaturedMetaList() {
//        return featuredService.getPinnedFeaturedList();
//    }
//
//    @GetMapping("/{featuredId}")
//    public FeaturedDetailResponseDto getFeatured(@PathVariable Long featuredId) {
//        return featuredService.getFeatured(featuredId);
//    }

}
