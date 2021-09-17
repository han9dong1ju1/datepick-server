package app.hdj.datepick.domain.review.repository;

import app.hdj.datepick.domain.review.entity.PlaceReview;
import app.hdj.datepick.domain.review.entity.PlaceReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceReviewRepository  extends
        JpaRepository<PlaceReview, Long>,
        PlaceReviewCustomRepository{

}
