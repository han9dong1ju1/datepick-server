package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.course.dto.CourseInfo;
import app.hdj.datepick.domain.featured.dto.FeaturedDetail;
import app.hdj.datepick.domain.featured.dto.QCourseInfo;
import app.hdj.datepick.domain.featured.entity.Featured;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
public class FeaturedSupportRepositoryImpl extends QuerydslRepositorySupport implements FeaturedSupportRepository {

    @PersistenceContext
    private final EntityManager em;

    private final QFeatured qFeatured = QFeatured.featured;
    private final QCourse qCourse = QCourse.course;
    private final QCourseFeaturedRelation qCourseFeaturedRelation = QCourseFeaturedRelation.courseFeaturedRelation;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public FeaturedSupportRepositoryImpl(EntityManager em) {
        super(Featured.class);
        this.em = em;
        jpaQueryFactory = new JPAQueryFactory(em);

    }

    @Override
    public FeaturedDetail findDetailBy(Long id) {

        Featured featured = jpaQueryFactory.selectFrom(qFeatured).where(qFeatured.id.eq(id)).fetchOne();
        //featured = from(qFeatured).where(qFeatured.id.eq(id)).fetchOne();
        System.out.println(featured.toString());

//        List<FeaturedDetail.CourseInfo> courseInfos = jpaQueryFactory
//                .select(new QFeaturedDetail_CourseInfo(qCourseFeaturedRelation.order, qCourseFeaturedRelation.course))
//                .from(qCourseFeaturedRelation)
//                .where(qCourseFeaturedRelation.featured.id.eq(id)).fetch();
//        System.out.println(courseInfos);
        List<CourseInfo> courseInfos = jpaQueryFactory
                .select(new QCourseInfo(qCourseFeaturedRelation.order, qCourseFeaturedRelation.course))
                .from(qCourseFeaturedRelation)
                .where(qCourseFeaturedRelation.featured.id.eq(id))
                .fetch();

        FeaturedDetail featuredDetail = FeaturedDetail.builder()
                .featured(featured)
                .courseInfos(courseInfos)
                .build();
        System.out.println(featuredDetail.toString());

        return featuredDetail;
    }
}
