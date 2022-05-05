package app.hdj.datepick.domain.course.repository;

import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import org.springframework.data.domain.Page;

public interface CourseCustomRepository {

    Page<Course> findPublicCoursePage(
        CourseFilterParam courseFilterParam, PagingParam pagingParam, CustomSort sort
    );

    Page<Course> findCoursePage(
        CourseFilterParam courseFilterParam, PagingParam pagingParam, CustomSort sort
    );

    Page<Course> findPickedCoursePage(
        CourseFilterParam courseFilterParam, PagingParam pagingParam, CustomSort sort, Long userId
    );
}
