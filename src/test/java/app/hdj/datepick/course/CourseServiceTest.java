package app.hdj.datepick.course;

import app.hdj.datepick.domain.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class CourseServiceTest {
    @Autowired
    CourseService courseService;

    public void getPickedCoursePageTest(){
        Pageable pageable = PageRequest.of(1, 10);
        courseService.getPickedCoursePage(pageable);
    }
}
