package app.hdj.datepick.domain.featured.service;

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

    public CustomPage<Featured> getFeaturedPage(Boolean isPinned, Long courseId, PagingParam pagingParam) {
        // 페이징된 데이터 가져옴
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(), Sort.by("createdAt").descending());
        Page<Featured> featuredPage = featuredRepository.findFeaturedPage(isPinned, courseId, pageRequest);

        // CustomPage로 말아서 반환
        return new CustomPage<>(
                featuredPage.getTotalElements(),
                featuredPage.getTotalPages(),
                featuredPage.getNumber(),
                featuredPage.getContent()
        );
    }

    public Featured getFeatured(Long featuredId) {
        return featuredRepository.findById(featuredId).orElseThrow();
    }

}
