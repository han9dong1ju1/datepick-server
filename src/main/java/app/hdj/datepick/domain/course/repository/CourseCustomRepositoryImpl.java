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
        QCourseFeaturedRelation courseFeatured = new QCourseFeaturedRelation("courseFeatured");
        return query
                .leftJoin(course.courseFeatureds, courseFeatured)
                .where(courseFeatured.featured.id.eq(featuredId))
                .orderBy(courseFeatured.courseOrder.asc());
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

}
