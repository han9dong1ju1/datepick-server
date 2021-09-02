package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.course.dto.CourseInfo;
import app.hdj.datepick.domain.course.dto.QCourseInfo;
import app.hdj.datepick.domain.course.entity.QCourse;
import app.hdj.datepick.domain.featured.dto.FeaturedDetail;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.entity.QFeatured;
import app.hdj.datepick.global.common.entity.QCourseFeaturedRelation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeaturedRepositoryImpl implements FeaturedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final QFeatured qFeatured = QFeatured.featured;
    private final QCourse qCourse = QCourse.course;
    private final QCourseFeaturedRelation qCourseFeaturedRelation = QCourseFeaturedRelation.courseFeaturedRelation;

    @Override
    public FeaturedDetail findDetailBy(Long id) {

        Featured featured = jpaQueryFactory
                .selectFrom(qFeatured)
                .where(qFeatured.id.eq(id))
                .fetchOne();

        List<CourseInfo> courseInfos = jpaQueryFactory
                .select(new QCourseInfo(qCourseFeaturedRelation.courseOrder, qCourseFeaturedRelation.course))
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
