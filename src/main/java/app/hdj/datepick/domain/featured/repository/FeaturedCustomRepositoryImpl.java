package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.relation.entity.CourseFeaturedRelation;
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
    public Page<Featured> findFeaturedPageByIsPinnedAndCourseId(Boolean isPinned, Long courseId, Pageable pageable) {

        List<Featured> results = jpaQueryFactory
                .select(featured)
                .from(courseFeaturedRelation)
                .innerJoin(courseFeaturedRelation.featured, featured)
                .where(courseFeaturedRelation.course.id.eq(courseId))
                .where(featured.isPinned.eq(isPinned))
                .distinct()
                .fetch();

        return new PageImpl<>(results, pageable, results.size());

    }
//    @Override
//    public Page<Featured> findFeaturedByIds(List<Long> featuredIds, Pageable pageable) {
//
//        List<Featured> results = jpaQueryFactory
//                .selectFrom(featured)
//                .where(featured.id.in(featuredIds))
//                .fetch();
//        System.out.println(pageable.toString());
//        return new PageImpl<>(results, pageable, results.size());
//    }
//
//    @Override
//    public List<Long> findFeaturedIdsByIsPinnedAndCourseId(Boolean isPinned, Long courseId) {
//        return jpaQueryFactory
//                .select(courseFeaturedRelation.featured.id)
//                .from(courseFeaturedRelation)
//                .where(courseFeaturedRelation.course.id.eq(courseId))
//                .where(courseFeaturedRelation.featured.isPinned.eq(isPinned))
//                .fetch();
//    }



//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());

        //정렬
//        for (Sort.Order o : pageable.getSort()){
//            PathBuilder pathBuilder = new PathBuilder(featured.getType(), featured.getMetadata());
//            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
//        }
//        List<Featured> results = query.fetch();


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
