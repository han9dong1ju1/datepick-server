package app.hdj.datepick.domain.place.repository;
import app.hdj.datepick.domain.place.dto.*;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;
import app.hdj.datepick.domain.review.dto.PlaceReviewDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static app.hdj.datepick.domain.pick.entity.QPlacePick.placePick;
import static app.hdj.datepick.domain.place.entity.QPlace.place;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     *
     * @param placeId 타깃 place id
     * @param userId 타깃 user id
     * @return user가 place를 pick 했는지 여부
     */
    @Override
    public Boolean isUserPickedPlace(Long placeId, Long userId){
        Long isExistUserId = jpaQueryFactory
                .select(placePick.user.id)
                .from(placePick)
                .where(placePick.user.id.eq(userId), placePick.place.id.eq(placeId))
                .fetchFirst();
        Boolean isPicked = true;
        if (isExistUserId == null){
            isPicked = false;
        }
        return isPicked;
    }

    /**
     *
     * @param placeId 찾을 place id
     * @param isPicked 요청한 user가 타깃 place id를 픽했는지 여부
     * @param reviews place에 작성된 review list
     * @return 결과를 조합한 PlaceDetailDto instance
     */
    @Override
    public PlaceDetailDto findPlaceDetail(Long placeId, Boolean isPicked, List<PlaceReviewDto> reviews){
        //TODO 결과가 2개 이상이면 NonUniqueResultException 발생
        PlaceDetailDto placeDetailDto = jpaQueryFactory
                .select(new QPlaceDetailDto(
                        place.id,
                        place.kakaoId,
                        place.name,
                        place.rating,
                        place.address,
                        place.latitude,
                        place.longitude,
                        place.type,
                        place.subtype,
                        place.category,
                        Expressions.constant(isPicked),
                        Expressions.constant(reviews)
                ))
                .from(place)
                .where(place.id.eq(placeId))
                .fetchOne();

        if (placeDetailDto == null){
            //TODO exception 아이디에 맞는 장소가 없음.
        }
        return placeDetailDto;
    }

    @Override
    public Page<PlaceMetaDto> findPlaceMetaListById(List<Long> placeIds, Pageable pageable) {

        JPAQuery<PlaceMetaDto> query = jpaQueryFactory
                .select(
                        new QPlaceMetaDto(
                                place.id,
                                place.kakaoId,
                                place.name,
                                place.rating,
                                place.address,
                                place.latitude,
                                place.longitude,
                                place.type,
                                place.subtype,
                                place.category
                        )
                )
                .from(place)
                .where(place.id.in(placeIds))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        //정렬 적용
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(place.getType(), place.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        QueryResults<PlaceMetaDto> results = query.fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}

