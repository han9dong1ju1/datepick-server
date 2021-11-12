package app.hdj.datepick.domain.review.repository;

import app.hdj.datepick.domain.review.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface PlaceReviewRepository  extends
        JpaRepository<PlaceReview, Long>,
        PlaceReviewCustomRepository,
        QuerydslPredicateExecutor<PlaceReview> {



}
