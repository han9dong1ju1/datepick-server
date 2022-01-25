package app.hdj.datepick.domain.diary.repository;

import app.hdj.datepick.domain.diary.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface PlaceReviewRepository  extends
        JpaRepository<PlaceReview, Long>,
        PlaceReviewCustomRepository,
        QuerydslPredicateExecutor<PlaceReview> {



}
