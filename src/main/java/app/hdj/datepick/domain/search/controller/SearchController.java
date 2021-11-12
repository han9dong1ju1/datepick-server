package app.hdj.datepick.domain.search.controller;

import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.search.dto.GeoPointDto;
import app.hdj.datepick.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/search")
public class SearchController {

    private final SearchService searchService;

    // Place 키워드 검색
    @GetMapping("places/keyword/{keyword}")
    public Page<PlaceMetaDto> keywordSearchPlace(@PathVariable String keyword, @RequestParam Pageable pageable) {
        return null;
    }

    // Place 위치 기반 검색
    @GetMapping("places/geo")
    public Page<PlaceMetaDto> geoSearchPlace(@Valid @ModelAttribute GeoPointDto geopointDto, Pageable pageable) {
        return searchService.geoSearchPlace(geopointDto, pageable);
    }

    // Course 키워드 검색
    @GetMapping("courses/keyword/{keyword}")
    public Page<CourseMetaDto> keywordSearchCourse(@PathVariable String keyword, @RequestParam Pageable pageable) {
        return null;
    }

}
