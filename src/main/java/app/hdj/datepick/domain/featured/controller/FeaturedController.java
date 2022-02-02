package app.hdj.datepick.domain.featured.controller;

import app.hdj.datepick.domain.featured.params.FeaturedRequestParam;
import app.hdj.datepick.domain.featured.service.FeaturedService;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.Sort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    @GetMapping("")
    public FeaturedRequestParam getFeaturedMetaList(@RequestParam(value = "is_pinned") Boolean isPinned,
                                                    @RequestParam(value = "course_id") Long courseId,
                                                    @RequestParam(value = "page") Long page,
                                                    @RequestParam(value = "size") Long size,
                                                    @RequestParam(value = "sort") String sort
                                                    ) {

        FeaturedRequestParam requestParam = new FeaturedRequestParam(isPinned, courseId);
        PagingParam pagingParam = new PagingParam(page, size, Sort.from(sort));

        return requestParam;
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
