package app.hdj.datepick.domain.review.repository;


import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;
import app.hdj.datepick.domain.review.dto.PlaceReviewDto;
import app.hdj.datepick.domain.review.dto.QPlaceReviewDto;
import app.hdj.datepick.domain.review.entity.PlaceReview;
import app.hdj.datepick.domain.review.entity.QPlaceReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static app.hdj.datepick.domain.review.entity.QPlaceReview.placeReview;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceReviewCustomRepositoryImpl implements PlaceReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PlaceReviewDto> findConstReviewsWithPlaceId(Long placeId) {
        List<PlaceReviewDto> placeReviewDtos = jpaQueryFactory
                .select(new QPlaceReviewDto(
                        placeReview.placeOrder,
                        placeReview.rating,
                        placeReview.content,
                        placeReview.place.id
                ))
                .from(placeReview)
                .where(placeReview.place.id.eq(placeId))
                .limit(5)
                .orderBy(placeReview.createdAt.desc())
                .fetch();
        return placeReviewDtos;
    }

    @Override
    public Page<String> findAllPhotoUrls(Long placeId, Pageable pageable) {
        return null;
    }

    @Override
    public List<PlaceReview> findAllByDiaryId(Long diaryId) {
        return jpaQueryFactory.selectFrom(placeReview).where(placeReview.diary.id.eq(diaryId)).fetch();
    }

    @Override
    public List<PlaceReviewDto> findPlaceReviewDtoByDiaryId(Long diaryId) {
        return jpaQueryFactory.select(
                new QPlaceReviewDto(
                        placeReview.placeOrder,
                        placeReview.rating,
                        placeReview.content,
                        placeReview.place.id
                ))
                .from(placeReview)
                .where(placeReview.diary.id.eq(diaryId)).fetch();
    }
}
