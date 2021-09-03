package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.course.dto.CourseFeaturedInfo;
import app.hdj.datepick.domain.course.dto.QCourseFeaturedInfo;
import app.hdj.datepick.domain.featured.dto.FeaturedDetail;
import app.hdj.datepick.domain.featured.dto.FeaturedMeta;
import app.hdj.datepick.domain.featured.entity.Featured;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static app.hdj.datepick.domain.featured.entity.QFeatured.featured;
import static app.hdj.datepick.global.common.entity.QCourseFeaturedRelation.courseFeaturedRelation;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class FeaturedCustomRepositoryImpl implements FeaturedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public FeaturedDetail findDetailById(Long id) {
        Featured foundFeatured = jpaQueryFactory
                .selectFrom(featured)
                .where(featured.id.eq(id))
                .fetchOne();

        List<CourseFeaturedInfo> courseFeaturedInfos = jpaQueryFactory
                .from(courseFeaturedRelation)
                .where(courseFeaturedRelation.featured.id.eq(id))
                .select(new QCourseFeaturedInfo(courseFeaturedRelation.order, courseFeaturedRelation.course))
                .fetch();

        //TODO Mapper 사용?
        FeaturedMeta featuredMeta = FeaturedMeta.builder()
                .id(foundFeatured.getId())
                .title(foundFeatured.getTitle())
                .description(foundFeatured.getDescription())
                .photoUrl(foundFeatured.getPhotoUrl())
                .build();

        FeaturedDetail featuredDetail = FeaturedDetail.builder()
                .meta(featuredMeta)
                .content(foundFeatured.getContent())
                .detail(courseFeaturedInfos)
                .build();

        return featuredDetail;
    }
}
