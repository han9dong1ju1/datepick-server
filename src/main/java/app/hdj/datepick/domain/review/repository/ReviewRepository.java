package app.hdj.datepick.domain.review.repository;

import app.hdj.datepick.domain.review.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReviewRepository extends
        JpaRepository<PlaceReview, Long>,
        PlaceReviewCustomRepository,
        QuerydslPredicateExecutor<PlaceReview> {

}
