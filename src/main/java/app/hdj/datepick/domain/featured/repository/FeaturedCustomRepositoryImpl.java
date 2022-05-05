package app.hdj.datepick.domain.featured.repository;

import static app.hdj.datepick.domain.featured.entity.QFeatured.featured;
import static app.hdj.datepick.domain.relation.entity.QCourseFeaturedRelation.courseFeaturedRelation;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class FeaturedCustomRepositoryImpl implements FeaturedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public Page<Featured> findFeaturedPage(Boolean isPinned, Long courseId, Pageable pageable) {
        JPAQuery<Featured> query = jpaQueryFactory.select(featured);

        // from
        if (courseId != null) {
            query.from(courseFeaturedRelation)
                .innerJoin(courseFeaturedRelation.featured, featured)
                .where(courseFeaturedRelation.course.id.eq(courseId));
        } else {
            query.from(featured);
        }

        // where
        if (isPinned != null && isPinned) {
            query.where(featured.isPinned.eq(true));
        } else if (isPinned != null) {
            query.where(featured.isPinned.eq(false));
        }

        return pagingUtil.getPageImpl(pageable, query);
    }
}
