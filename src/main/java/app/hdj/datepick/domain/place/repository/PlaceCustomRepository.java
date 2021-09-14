package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceCourseMetaDto;
import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;

import java.util.List;

public interface PlaceCustomRepository {

    Boolean IsUserPickedPlace(Long placeId, Long userId);
    PlaceDetailDto findPlaceDetail(Long placeId, Boolean isPicked, List<String> photoUrls);
    List<String> findReviewPhotoUrls(Long placeId);

    //TODO security user id
    List<Long> findPickedPlaceIds(Long userId);
    List<PlaceMetaDto> findPlaceMetasWithIds(List<Long> placeIds);

    List<PlaceCourseMetaDto> findPlaceCourseMetas(Long courseId);

    PlaceMetaDto patch(PlaceRequestDto placeRequestDto);
    PlaceMetaDto post(PlaceRequestDto placeRequestDto);

}
