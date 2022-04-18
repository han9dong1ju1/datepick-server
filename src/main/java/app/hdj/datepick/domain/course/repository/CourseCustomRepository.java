package app.hdj.datepick.domain.course.repository;

import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.global.common.PagingParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface CourseCustomRepository {

    Page<Course> findPublicCoursePage(CourseFilterParam courseFilterParam, PagingParam pagingParam, Sort sort);

    Page<Course> findCoursePage(CourseFilterParam courseFilterParam, PagingParam pagingParam, Sort sort);

    Page<Course> findPickedCoursePage(CourseFilterParam courseFilterParam, PagingParam pagingParam, Sort sort, Long userId);
}
