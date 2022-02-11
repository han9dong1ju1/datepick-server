package app.hdj.datepick.domain.diary.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceReviewCustomRepositoryImpl implements PlaceReviewCustomRepository {
//
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<PlaceReviewDto> findConstReviewsWithPlaceId(Long placeId) {
//        List<PlaceReviewDto> placeReviewDtos = jpaQueryFactory
//                .select(new QPlaceReviewDto(
//                        placeReview.placeOrder,
//                        placeReview.rating,
//                        placeReview.content,
//                        placeReview.place.id
//                ))
//                .from(placeReview)
//                .where(placeReview.place.id.eq(placeId))
//                .limit(5)
//                .orderBy(placeReview.createdAt.desc())
//                .fetch();
//        return placeReviewDtos;
//    }
//
//    @Override
//    public Page<String> findAllPhotoUrls(Long placeId, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<PlaceReview> findAllByDiaryId(Long diaryId) {
//        return jpaQueryFactory.selectFrom(placeReview).where(placeReview.diary.id.eq(diaryId)).fetch();
//    }
//
//    @Override
//    public List<PlaceReviewDto> findPlaceReviewDtoByDiaryId(Long diaryId) {
//        return jpaQueryFactory.select(
//                new QPlaceReviewDto(
//                        placeReview.placeOrder,
//                        placeReview.rating,
//                        placeReview.content,
//                        placeReview.place.id
//                ))
//                .from(placeReview)
//                .where(placeReview.diary.id.eq(diaryId)).fetch();
//    }
}
