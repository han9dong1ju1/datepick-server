package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.relation.repository.CourseFeaturedRelationRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
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
    public Page<Featured> findFeaturedByIsPinnedAndCourseId(Boolean isPinned, Long courseId, Pageable pageable) {
        System.out.println(isPinned);
        System.out.println(courseId);
        System.out.println(pageable.toString());
        List<Featured> results = jpaQueryFactory
                .select(featured)
                .innerJoin(courseFeaturedRelation.featured, featured)
                //.innerJoin(courseFeaturedRelation.featured, featured)
                .where(courseFeaturedRelation.course.id.eq(courseId))
                .fetch();

//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());

        //정렬
//        for (Sort.Order o : pageable.getSort()){
//            PathBuilder pathBuilder = new PathBuilder(featured.getType(), featured.getMetadata());
//            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
//        }
//        List<Featured> results = query.fetch();

        return new PageImpl<>(results, pageable, results.size());
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
