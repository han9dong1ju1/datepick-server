package app.hdj.datepick.domain.review.repository;


import app.hdj.datepick.domain.review.dto.PlaceReviewDto;
import app.hdj.datepick.domain.review.dto.QPlaceReviewDto;
import app.hdj.datepick.domain.review.entity.QPlaceReview;
import app.hdj.datepick.domain.review.entity.QPlaceReviewPhoto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.hdj.datepick.domain.review.entity.QPlaceReview.placeReview;
import static app.hdj.datepick.domain.review.entity.QPlaceReviewPhoto.placeReviewPhoto;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceReviewCustomRepositoryImpl implements PlaceReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PlaceReviewDto> findConstReviewsWithPlaceId(Long placeId) {
        return null;
    }




    @Override
    public Page<String> findAllPhotoUrls(Long placeId, Pageable pageable) {
        return null;
    }
}
