package app.hdj.datepick.domain.diary.repository;

import app.hdj.datepick.domain.diary.entity.PlaceReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceReviewPhotoRepository extends
        JpaRepository<PlaceReviewPhoto, Long>,
        PlaceReviewPhotoCustomRepository {
}
