package app.hdj.datepick.domain.featured.controller;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.param.FeaturedRequestParam;
import app.hdj.datepick.domain.featured.service.FeaturedService;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    @GetMapping("")
    public CustomPage<Featured> getFeaturedPage(@Valid FeaturedRequestParam featuredRequestParam,
                                                @Valid PagingParam pagingParam) {
        return featuredService.getFeaturedPage(featuredRequestParam, pagingParam);
    }

    @GetMapping("/{featuredId}")
    public Featured getFeatured(@PathVariable Long featuredId) {
        return featuredService.getFeatured(featuredId);
    }

}
