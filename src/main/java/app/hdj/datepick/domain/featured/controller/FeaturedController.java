package app.hdj.datepick.domain.featured.controller;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.params.FeaturedRequestParam;
import app.hdj.datepick.domain.featured.service.FeaturedService;
import app.hdj.datepick.domain.relation.entity.CourseFeaturedRelation;
import app.hdj.datepick.global.common.PagingParam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    @GetMapping("")
    public Page<Featured> getFeaturedMetaList(@RequestParam(value = "is_pinned", defaultValue = "true") Boolean isPinned,
                                              @RequestParam(value = "course_id", required = false) Long courseId,
                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "size", defaultValue = "20") int size,
                                              @RequestParam(value = "sort", required = false) String sort
                                                    ) {
        Pageable pageable;
        if (sort.toLowerCase() == "asc"){
            pageable = PageRequest.of(page, size, Sort.by("created_at").ascending());
        }else{
            pageable = PageRequest.of(page, size, Sort.by("created_at").descending());
        }

        return featuredService.getFeaturedPage(isPinned, courseId, pageable);

    }
    @GetMapping("/{featuredId}")
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
