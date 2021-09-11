package app.hdj.datepick.data;


import app.hdj.datepick.domain.model.Place;
import org.junit.jupiter.api.Test;
import app.hdj.datepick.domain.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlaceTest {

    PlaceRepository placeRepository;

    @Autowired
    public PlaceTest(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Test
    public void findPlacesWhereInCourseTest(){
        Long courseId = 5L;
        List<Place> places = placeRepository.findPlacesWhereInCourse(courseId);
        return;
    }

}
