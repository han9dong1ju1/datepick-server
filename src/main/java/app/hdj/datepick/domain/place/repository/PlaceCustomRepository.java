package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceDetailDto;

import java.util.List;

public interface PlaceCustomRepository {

    Boolean IsUserPickedPlace(Long placeId, Long userId);
    PlaceDetailDto findPlaceDetail(Long placeId, Boolean isPicked, List<String> photoUrls);
    List<String> findReviewPhotoUrls(Long placeId);
}
