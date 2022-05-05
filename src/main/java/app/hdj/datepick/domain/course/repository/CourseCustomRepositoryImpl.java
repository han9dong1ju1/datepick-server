package app.hdj.datepick.domain.course.repository;

import static app.hdj.datepick.domain.course.entity.QCourse.course;

import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.relation.entity.QCourseFeaturedRelation;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CourseCustomRepositoryImpl implements CourseCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public Page<Course> findPublicCoursePage(
        CourseFilterParam courseFilterParam, PagingParam pagingParam, CustomSort sort
    ) {
        JPQLQuery<Course> query = jpaQueryFactory.selectFrom(course)
            .where(course.isPrivate.eq(false));
        query = filterCourses(query, courseFilterParam);
        query = applySort(query, sort);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize());
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    @Override
    public Page<Course> findCoursePage(
        CourseFilterParam courseFilterParam, PagingParam pagingParam, CustomSort sort
    ) {
        JPQLQuery<Course> query = jpaQueryFactory.selectFrom(course);
        query = filterCourses(query, courseFilterParam);
        query = applySort(query, sort);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize());
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    @Override
    public Page<Course> findPickedCoursePage(
        CourseFilterParam courseFilterParam, PagingParam pagingParam, CustomSort sort, Long userId
    ) {
        JPQLQuery<Course> query = jpaQueryFactory.selectFrom(course)
            .where(course.isPrivate.eq(false)).where(course.coursePicks.any().user.id.eq(userId));
        query = filterCourses(query, courseFilterParam);
        query = applySort(query, sort);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize());
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    private JPQLQuery<Course> filterCourses(
        JPQLQuery<Course> query, CourseFilterParam courseFilterParam
    ) {
        if (courseFilterParam.getKeyword() != null) {
            query = query.where(course.title.contains(courseFilterParam.getKeyword()));
        }

        if (courseFilterParam.getUserId() != null) {
            query = query.where(course.user.id.eq(courseFilterParam.getUserId()));
        }

        if (courseFilterParam.getFeaturedId() != null) {
            QCourseFeaturedRelation courseFeatured = new QCourseFeaturedRelation("courseFeatured");
            query = query.leftJoin(course.courseFeatureds, courseFeatured)
                .where(courseFeatured.featured.id.eq(courseFilterParam.getFeaturedId()))
                .orderBy(courseFeatured.courseOrder.asc());
        }

        if (courseFilterParam.getPlaceId() != null) {
            query = query.where(
                course.coursePlaces.any().place.id.eq(courseFilterParam.getPlaceId()));
        }

        if (courseFilterParam.getTagId() != null && !courseFilterParam.getTagId().isEmpty()) {
            for (Integer id : courseFilterParam.getTagId()) {
                query = query.where(course.courseTags.any().tag.id.eq(id));
            }
        }

        return query;
    }

    private JPQLQuery<Course> applySort(JPQLQuery<Course> query, CustomSort sort) {
        if (sort == null) {
            sort = CustomSort.LATEST;
        }

        switch (sort) {
            case LATEST:
                query = query.orderBy(course.createdAt.desc());
            case PICK:
                query = query.orderBy(course.coursePicks.size().desc());
                break;
            case POPULAR:
                query = query.orderBy(course.viewCount.desc());  // TODO: 인기도 기준 재설정
        }

        return query;
    }
}
