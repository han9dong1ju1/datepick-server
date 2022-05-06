package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.dto.FeaturedFilterParam;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.repository.FeaturedRepository;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedService {

    private final FeaturedRepository featuredRepository;

    public CustomPage<Featured> getFeaturedPage(
        FeaturedFilterParam featuredFilterParam, PagingParam pagingParam
    ) {
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(),
                                                 pagingParam.getSize(),
                                                 Sort.by("createdAt").descending());
        Page<Featured> featuredPage = featuredRepository.findFeaturedPage(featuredFilterParam.getIsPinned(),
                                                                          featuredFilterParam.getCourseId(),
                                                                          pageRequest);
        return CustomPage.from(featuredPage);
    }

    public Featured getFeatured(Long featuredId) {
        return featuredRepository.findById(featuredId).orElseThrow();
    }
}
