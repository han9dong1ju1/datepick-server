package app.hdj.datepick.domain.course.repository;

import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.relation.entity.QCourseFeaturedRelation;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.hdj.datepick.domain.course.entity.QCourse.course;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CourseCustomRepositoryImpl implements CourseCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public Page<Course> findPublicCoursePage(CourseFilterParam courseFilterParam, PagingParam pagingParam, Sort sort) {
        JPQLQuery<Course> query = jpaQueryFactory
                .selectFrom(course)
                .where(course.isPrivate.eq(false));
        query = filterCourses(query, courseFilterParam);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(), courseFilterParam.getSort(sort));
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    @Override
    public Page<Course> findCoursePage(CourseFilterParam courseFilterParam, PagingParam pagingParam, Sort sort) {
        JPQLQuery<Course> query = jpaQueryFactory
                .selectFrom(course);
        query = filterCourses(query, courseFilterParam);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(), courseFilterParam.getSort(sort));
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    @Override
    public Page<Course> findPickedCoursePage(CourseFilterParam courseFilterParam, PagingParam pagingParam, Sort sort, Long userId) {
        JPQLQuery<Course> query = jpaQueryFactory
                .selectFrom(course)
                .where(course.isPrivate.eq(false))
                .where(course.coursePicks.any().user.id.eq(userId));
        query = filterCourses(query, courseFilterParam);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(), courseFilterParam.getSort(sort));
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    private JPQLQuery<Course> filterCourses(JPQLQuery<Course> query, CourseFilterParam courseFilterParam) {
        if (courseFilterParam.getKeyword() != null) {
            query = filterKeyword(courseFilterParam.getKeyword(), query);
        }

        if (courseFilterParam.getUserId() != null) {
            query = filterUser(courseFilterParam.getUserId(), query);
        }

        if (courseFilterParam.getFeaturedId() != null) {
            query = filterFeatured(courseFilterParam.getFeaturedId(), query);
        }

        if (courseFilterParam.getPlaceId() != null) {
            query = filterPlace(courseFilterParam.getPlaceId(), query);
        }

        if (courseFilterParam.getTagId() != null && !courseFilterParam.getTagId().isEmpty()) {
            query = filterTags(courseFilterParam.getTagId(), query);
        }

        return query;
    }

    private JPQLQuery<Course> filterKeyword(String keyword, JPQLQuery<Course> query) {
        return query.where(course.title.contains(keyword));
    }

    private JPQLQuery<Course> filterUser(Long userId, JPQLQuery<Course> query) {
        return query.where(course.user.id.eq(userId));
    }

    private JPQLQuery<Course> filterFeatured(Long featuredId, JPQLQuery<Course> query) {
        QCourseFeaturedRelation courseFeatureds = new QCourseFeaturedRelation("courseFeatureds");
        return query
                .leftJoin(course.courseFeatureds, courseFeatureds)
                .where(courseFeatureds.featured.id.eq(featuredId))
                .orderBy(courseFeatureds.courseOrder.asc());
    }

    private JPQLQuery<Course> filterPlace(Long placeId, JPQLQuery<Course> query) {
        return query.where(course.coursePlaces.any().place.id.eq(placeId));
    }

    private JPQLQuery<Course> filterTags(List<Byte> tagId, JPQLQuery<Course> query) {
        for (Byte id : tagId) {
            query = query.where(course.courseTags.any().tag.id.eq(id));
        }
        return query;
    }

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
}
