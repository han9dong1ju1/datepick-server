package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.course.dto.QCourseMetaDto;
import app.hdj.datepick.domain.featured.dto.FeaturedCourseDto;
import app.hdj.datepick.domain.featured.dto.QFeaturedCourseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static app.hdj.datepick.domain.course.entity.QCourse.course;
import static app.hdj.datepick.domain.relation.entity.QCourseFeaturedRelation.courseFeaturedRelation;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class FeaturedCustomRepositoryImpl implements FeaturedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FeaturedCourseDto> findCourseInFeaturedById(Long featuredId) {
        return null;
    }

    //    @Override
//    public List<FeaturedCourseDto> findCourseInFeaturedById(Long featuredId) {
//        return jpaQueryFactory
//                .select(new QFeaturedCourseDto(
//                        courseFeaturedRelation.courseOrder,
//                        new QCourseMetaDto(
//                                course.id,
//                                course.title,
//                                course.expectedAt,
//                                course.pickCount,
//                                course.user.id
//                        )))
//                .from(courseFeaturedRelation)
//                .innerJoin(courseFeaturedRelation.course, course)
//                .where(
//                        courseFeaturedRelation.featured.id.eq(featuredId)
//                )
//                .orderBy(courseFeaturedRelation.courseOrder.asc())
//                .fetch();
//    }
}
