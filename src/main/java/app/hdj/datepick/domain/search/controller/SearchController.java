package app.hdj.datepick.domain.search.controller;

import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.search.dto.SearchRequestDto;
import app.hdj.datepick.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("place")
    public Page<PlaceMetaDto> searchPlace(@Valid @RequestBody SearchRequestDto searchRequestDto) {
        return null;
    }

    @GetMapping("course")
    public Page<CourseMetaDto> searchCourse(@Valid @RequestBody SearchRequestDto searchRequestDto) {
        return null;
    }

}
