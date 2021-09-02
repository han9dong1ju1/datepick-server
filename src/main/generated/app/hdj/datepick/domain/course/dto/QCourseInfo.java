package app.hdj.datepick.domain.course.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * app.hdj.datepick.domain.course.dto.QCourseInfo is a Querydsl Projection type for CourseInfo
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QCourseInfo extends ConstructorExpression<CourseInfo> {

    private static final long serialVersionUID = 1637332403L;

    public QCourseInfo(com.querydsl.core.types.Expression<Byte> order, com.querydsl.core.types.Expression<? extends app.hdj.datepick.domain.course.entity.Course> course) {
        super(CourseInfo.class, new Class<?>[]{byte.class, app.hdj.datepick.domain.course.entity.Course.class}, order, course);
    }

}

