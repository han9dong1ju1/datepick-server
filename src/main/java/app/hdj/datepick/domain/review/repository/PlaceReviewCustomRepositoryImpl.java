package app.hdj.datepick.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class PlaceReviewCustomRepositoryImpl implements PlaceReviewCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<String> getPlaceReviewPhotos(Long placeReviewId) {
        return null;
    }
}
