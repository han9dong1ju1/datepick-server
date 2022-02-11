//package app.hdj.datepick.domain.course.repository;
//
//import app.hdj.datepick.domain.course.dto.*;
//import app.hdj.datepick.domain.place.dto.QPlaceMetaDto;
//import app.hdj.datepick.domain.relation.dto.CoursePlaceRelationDto;
//import app.hdj.datepick.domain.relation.dto.QCoursePlaceRelationDto;
//import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
//import com.querydsl.core.QueryResults;
//import com.querydsl.core.types.Order;
//import com.querydsl.core.types.OrderSpecifier;
//import com.querydsl.core.types.dsl.Expressions;
//import com.querydsl.core.types.dsl.PathBuilder;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//import static app.hdj.datepick.domain.course.entity.QCourse.course;
//import static app.hdj.datepick.domain.place.entity.QPlace.place;
//import static app.hdj.datepick.domain.relation.entity.QCoursePlaceRelation.coursePlaceRelation;
//
//@Slf4j
//@RequiredArgsConstructor
//@Repository
//public class CourseCustomRepositoryImpl implements CourseCustomRepository {
//
//    private final JPAQueryFactory jpaQueryFactory;
//
//
//    @Override
//    public List<Long> findPlaceIdListInCourse(Long courseId) {
//        return jpaQueryFactory
//                .select(coursePlaceRelation.place.id)
//                .from(coursePlaceRelation)
//                .where(coursePlaceRelation.course.id.eq(courseId))
//                .fetch();
//    }
//
//    @Override
//    public Page<CourseMetaDto> findCourseMetaPageById(List<Long> courseIds, Pageable pageable) {
//        JPAQuery<CourseMetaDto> query = jpaQueryFactory
//                .select(new QCourseMetaDto(
//                        course.id,
//                        course.title,
//                        course.region.stringValue(),
//                        course.expectedAt,
//                        course.pickCount,
//                        course.user.id
//                ))
//                .from(course)
//                .where(course.id.in(courseIds))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());
//        //정렬
//        for (Sort.Order o : pageable.getSort()){
//            PathBuilder pathBuilder = new PathBuilder(course.getType(), course.getMetadata());
//            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
//        }
//        QueryResults<CourseMetaDto> results = query.fetchResults();
//
//        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
//    }
//
//    @Override
//    public List<CourseMetaDto> findCourseMetaListById(List<Long> courseIds) {
//        return jpaQueryFactory
//                .select(new QCourseMetaDto(
//                        course.id,
//                        course.title,
//                        course.region.stringValue(),
//                        course.expectedAt,
//                        course.pickCount,
//                        course.user.id
//                ))
//                .from(course)
//                .where(course.id.in(courseIds))
//                .fetch();
//    }
//
//    @Override
//    public List<CoursePlaceDetailRelationDto> findPlaceRelationDtoInCourse(Long courseId, List<Long> placeIds) {
//        return jpaQueryFactory
//                .select(new QCoursePlaceDetailRelationDto(
//                        coursePlaceRelation.placeOrder,
//                        coursePlaceRelation.visitTime,
//                        coursePlaceRelation.memo,
//                        new QPlaceMetaDto(
//                                place.id,
//                                place.kakaoId,
//                                place.name,
//                                place.rating,
//                                place.address,
//                                place.latitude,
//                                place.longitude,
//                                place.type,
//                                place.subtype,
//                                place.category
//                        )
//                ))
//                .from(coursePlaceRelation)
//                .innerJoin(coursePlaceRelation.place, place)
//                .where(coursePlaceRelation.course.id.eq(courseId),
//                        coursePlaceRelation.place.id.in(placeIds))
//                .fetch();
//    }
//
//    @Override
//    public CourseDetailDto findCourseDetail(Long courseId, Boolean isPicked, List<CoursePlaceDetailRelationDto> placeRelations) {
//        return jpaQueryFactory
//                .select(new QCourseDetailDto(
//                        course.id,
//                        course.title,
//                        course.region.stringValue(),
//                        course.expectedAt,
//                        course.pickCount,
//                        course.user.id,
//                        //TODO thumbNail url column 지정
//                        Expressions.constant("null"),
//                        Expressions.constant(isPicked),
//                        Expressions.constant(placeRelations)
//                ))
//                .from(course)
//                .where(course.id.eq(courseId))
//                .fetchOne();
//    }
//
//    @Override
//    public List<CoursePlaceRelation> findPlaceRelationByCourseId(Long courseId){
//        return jpaQueryFactory
//                .selectFrom(coursePlaceRelation)
//                .where(coursePlaceRelation.course.id.eq(courseId))
//                .fetchAll().fetch();
//    }
//
//    @Override
//    public List<CoursePlaceRelationDto> findPlaceRelationDtoByCourseId(Long courseId){
//        return jpaQueryFactory
//                .select(new QCoursePlaceRelationDto(
//                        coursePlaceRelation.course.id,
//                        coursePlaceRelation.place.id,
//                        coursePlaceRelation.placeOrder,
//                        coursePlaceRelation.visitTime,
//                        coursePlaceRelation.memo
//                ))
//                .from(coursePlaceRelation)
//                .where(coursePlaceRelation.course.id.eq(courseId))
//                .fetchAll().fetch();
//    }
//
//    @Override
//    public void updatePlaceRelations(Long courseId, ModifyCoursePlaceRelationDto placeRelation) {
//        jpaQueryFactory.update(coursePlaceRelation)
//                .where(coursePlaceRelation.course.id.eq(courseId))
//                .where(coursePlaceRelation.placeOrder.eq(placeRelation.getPlaceOrder()))
//                .set(coursePlaceRelation.placeOrder, placeRelation.getPlaceOrder())
//                .set(coursePlaceRelation.memo, placeRelation.getMemo())
//                .set(coursePlaceRelation.visitTime, placeRelation.getVisitTime())
//                .set(coursePlaceRelation.place.id, placeRelation.getPlaceId())
//                .execute();
//    }
//
//    @Override
//    public void insertPlaceRelations(Long courseId, ModifyCoursePlaceRelationDto placeRelation) {
//        jpaQueryFactory.insert(coursePlaceRelation)
//                .set(coursePlaceRelation.placeOrder, placeRelation.getPlaceOrder())
//                .set(coursePlaceRelation.memo, placeRelation.getMemo())
//                .set(coursePlaceRelation.visitTime, placeRelation.getVisitTime())
//                .set(coursePlaceRelation.place.id, placeRelation.getPlaceId())
//                .execute();
//    }
//
//    @Override
//    public Long deletePlaceRelations(Long courseId, List<Long> newPlaceIds) {
//        return jpaQueryFactory.delete(coursePlaceRelation)
//                .where(coursePlaceRelation.course.id.eq(courseId))
//                .where(coursePlaceRelation.place.id.notIn(newPlaceIds))
//                .execute();
//    }
//
//    @Override
//    public void createCoursePlaceRelation(Long courseId, List<ModifyCoursePlaceRelationDto> placeRelations) {
//        for (ModifyCoursePlaceRelationDto placeRelation: placeRelations){
//            jpaQueryFactory.insert(coursePlaceRelation)
//                    .set(coursePlaceRelation.placeOrder, placeRelation.getPlaceOrder())
//                    .set(coursePlaceRelation.memo, placeRelation.getMemo())
//                    .set(coursePlaceRelation.visitTime, placeRelation.getVisitTime())
//                    .set(coursePlaceRelation.place.id, placeRelation.getPlaceId())
//                    .execute();
//        }
//    }
//}
