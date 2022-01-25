package app.hdj.datepick.domain.diary.repository;


import app.hdj.datepick.domain.diary.dto.PlaceReviewDto;
import app.hdj.datepick.domain.diary.dto.QPlaceReviewDto;
import app.hdj.datepick.domain.diary.entity.PlaceReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.hdj.datepick.domain.diary.entity.QPlaceReview.placeReview;


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
