package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedPage;
import app.hdj.datepick.domain.featured.dto.FeaturedPageRequest;
import app.hdj.datepick.domain.featured.entity.Featured;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static app.hdj.datepick.domain.featured.entity.QFeatured.featured;
import static app.hdj.datepick.domain.relation.entity.QCourseFeaturedRelation.courseFeaturedRelation;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class FeaturedCustomRepositoryImpl implements FeaturedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public FeaturedPage findFeaturedPageByIsPinnedAndCourseId(Boolean isPinned, Long courseId, FeaturedPageRequest featuredPageRequest) {

        List<Featured> results = jpaQueryFactory
                .select(featured)
                .from(courseFeaturedRelation)
                .innerJoin(courseFeaturedRelation.featured, featured)
                .where(courseFeaturedRelation.course.id.eq(courseId))
                .where(featured.isPinned.eq(isPinned))
                .fetch();

        Pageable pageable = PageRequest.of(featuredPageRequest.getPage(), featuredPageRequest.getSize(), featuredPageRequest.getSort());

        Page<Featured> featuredInfo = new PageImpl<>(results, pageable, results.size());
        return FeaturedPage.builder()
                .totalCount(featuredInfo.getTotalElements())
                .currentPage(featuredInfo.getNumber())
                .last(featuredInfo.isLast())
                .content(featuredInfo.getContent())
                .build();
    }

    @Override
    public FeaturedPage findFeaturedPageByIsPinned(Boolean isPinned, FeaturedPageRequest featuredPageRequest) {
        List<Featured> results = jpaQueryFactory
                .selectFrom(featured)
                .where(featured.isPinned.eq(isPinned))
                .fetch();

        Pageable pageable = PageRequest.of(featuredPageRequest.getPage(), featuredPageRequest.getSize(), featuredPageRequest.getSort());

        Page<Featured> featuredInfo = new PageImpl<>(results, pageable, results.size());

        return FeaturedPage.builder()
                .totalCount(featuredInfo.getTotalElements())
                .currentPage(featuredInfo.getNumber())
                .last(featuredInfo.isLast())
                .content(featuredInfo.getContent())
                .build();
    }
}
