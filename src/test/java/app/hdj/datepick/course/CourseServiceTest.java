package app.hdj.datepick.course;

import app.hdj.datepick.domain.course.repository.CourseRepository;
import app.hdj.datepick.domain.course.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


class CourseServiceTest {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Test
    void test(){
        courseRepository.test1();
    }

}
