package app.hdj.datepick.domain.photo.repository;

import app.hdj.datepick.domain.photo.entity.PlaceReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceReviewPhotoRepository extends
        JpaRepository<PlaceReviewPhoto, Long>,
        PlaceReviewPhotoCustomRepository {
}
