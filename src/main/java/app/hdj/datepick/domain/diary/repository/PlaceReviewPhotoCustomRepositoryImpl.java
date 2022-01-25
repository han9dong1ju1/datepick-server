package app.hdj.datepick.domain.diary.repository;



import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import static app.hdj.datepick.domain.photo.entity.QPlaceReviewPhoto.placeReviewPhoto;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceReviewPhotoCustomRepositoryImpl implements PlaceReviewPhotoCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<String> getPlaceReviewPhotoPage(Long placeId, Pageable pageable) {
        JPAQuery<String> query = jpaQueryFactory
                .select(placeReviewPhoto.photoUrl)
                .from(placeReviewPhoto)
                .where(placeReviewPhoto.placeReview.place.id.eq(placeId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(placeReviewPhoto.getType(), placeReviewPhoto.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        QueryResults<String> results = query.fetchResults();
        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }
}
