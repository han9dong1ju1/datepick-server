package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.course.dto.CourseFeaturedInfo;
import app.hdj.datepick.domain.course.dto.QCourseFeaturedInfo;
import app.hdj.datepick.domain.course.entity.QCourse;
import app.hdj.datepick.domain.featured.dto.FeaturedDetail;
import app.hdj.datepick.domain.featured.dto.FeaturedMeta;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.entity.QFeatured;
import app.hdj.datepick.global.common.entity.QCourseFeaturedRelation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
public class FeaturedCustomRepositoryImpl extends QuerydslRepositorySupport implements FeaturedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final QFeatured qFeatured = QFeatured.featured;
    private final QCourse qCourse = QCourse.course;
    private final QCourseFeaturedRelation qCourseFeaturedRelation = QCourseFeaturedRelation.courseFeaturedRelation;

    //EntityManeger 지정 생성자 필요
    @Autowired
    public FeaturedCustomRepositoryImpl(EntityManager em) {
        super(Featured.class);
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public FeaturedDetail findDetailById(Long id) {

        Featured featured = jpaQueryFactory
                .selectFrom(qFeatured)
                .where(qFeatured.id.eq(id))
                .fetchOne();
        List<CourseFeaturedInfo> courseFeaturedInfos = jpaQueryFactory
                .from(qCourseFeaturedRelation)
                .fetchAll()
                .where(qCourseFeaturedRelation.featured.id.eq(id))
                .select(new QCourseFeaturedInfo(qCourseFeaturedRelation.order, qCourseFeaturedRelation.course))
                .fetch();

        //TODO Mapper 사용?
        FeaturedMeta featuredMeta = FeaturedMeta.builder()
                .id(featured.getId())
                .title(featured.getTitle())
                .description(featured.getDescription())
                .photoUrl(featured.getPhotoUrl())
                .build();

        FeaturedDetail featuredDetail = FeaturedDetail.builder()
                .featuredMeta(featuredMeta)
                .content(featured.getContent())
                .featuredDetail(courseFeaturedInfos)
                .build();
        //

        return featuredDetail;
    }
}
