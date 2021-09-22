package app.hdj.datepick.domain.photo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceReviewPhotoCustomRepository {
    Page<String> getPlaceReviewPhotoPage(Long placeId, Pageable pageable);
}
