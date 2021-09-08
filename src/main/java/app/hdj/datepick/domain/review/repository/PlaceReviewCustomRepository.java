package app.hdj.datepick.domain.review.repository;

import java.util.List;

public interface PlaceReviewCustomRepository {
    List<String> getPlaceReviewPhotos(Long placeReviewId);
}
