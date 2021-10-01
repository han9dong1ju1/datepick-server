package app.hdj.datepick.domain.course.controller;

import app.hdj.datepick.domain.course.dto.CourseDetailDto;
import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.course.dto.request.CourseModifyRequsetDto;
import app.hdj.datepick.domain.course.service.CourseService;
import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{courseId}")
    public CourseDetailDto getCourse(@PathVariable Long courseId){
        return courseService.getCourse(courseId);
    }

    @GetMapping("")
    public Page<CourseMetaDto> getCourses(@RequestParam(value = "request_type") String requestType , Pageable pageable){
        System.out.println(requestType);
        if(requestType.equals("picked")){
            return courseService.getPickedCoursePage(pageable);
        }else if (requestType.equals("recommended")){
            //return courseService.getRecommendCoursePage(pageable);
        }
        //TODO RequestParam Validation Exception
        return null;
    }

    @PostMapping("/modify/{courseId}")
    public CourseDetailDto modifyCourse(@PathVariable Long courseId, @RequestBody CourseModifyRequsetDto courseModifyRequsetDto){

        return null;
    }

}
