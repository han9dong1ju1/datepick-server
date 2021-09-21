package app.hdj.datepick.domain.photo.repository;


import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.QPlaceMetaDto;
import app.hdj.datepick.domain.review.entity.QPlaceReviewPhoto;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.hdj.datepick.domain.review.entity.QPlaceReviewPhoto.placeReviewPhoto;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceReviewPhotoCustomRepositoryImpl implements PlaceReviewPhotoCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<String> getPlaceReviewPhotoPage(Long placeId, Pageable pageable) {
        QueryResults<String> result = jpaQueryFactory
                .select(placeReviewPhoto.photoUrl)
                .from(placeReviewPhoto)
                .where(placeReviewPhoto.placeReview.place.id.eq(placeId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(),pageable,result.getTotal());
    }
}
