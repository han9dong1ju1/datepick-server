package app.hdj.datepick.place;

import app.hdj.datepick.domain.photo.repository.PlaceReviewPhotoRepository;
import app.hdj.datepick.domain.pick.entity.QPlacePick;
import app.hdj.datepick.domain.pick.repository.PlacePickRepository;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.QPlaceMetaDto;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import app.hdj.datepick.domain.review.repository.PlaceReviewRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static app.hdj.datepick.domain.place.entity.QPlace.place;
import static org.junit.jupiter.api.Assertions.*;

class PlaceRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    PlacePickRepository placePickRepository;
    @Autowired
    PlaceReviewRepository placeReviewRepository;
    @Autowired
    PlaceReviewPhotoRepository placeReviewPhotoRepository;
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void isUserPickedPlace() {
//        Long placeId = 10L;
//        Long userId = 10L;
//        placeRepository.isUserPickedPlace(placeId, userId);
    }

    @Test
    void findPlaceDetail() {

    }

    @Test
    void getPlaces(){

    }



    @Test
    void findPlaceMetaListsById() {
        Long userId = 10L;
        List<Long> placeIds = jpaQueryFactory.select(QPlacePick.placePick.place.id).from(QPlacePick.placePick).where(QPlacePick.placePick.user.id.eq(userId)).fetch();
        //List<Long> placeIds = placePickRepository.findPickedPlaceIds(userId);
        System.out.println(placeIds);
    }
}