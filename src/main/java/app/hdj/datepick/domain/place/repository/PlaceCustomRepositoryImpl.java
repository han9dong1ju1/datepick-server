package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import app.hdj.datepick.global.util.GeoQueryUtil;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import static app.hdj.datepick.domain.place.entity.QPlace.place;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public Page<Place> findPlacePage(PlaceFilterParam placeFilterParam, PagingParam pagingParam, CustomSort sort) {
        JPQLQuery<Place> query = jpaQueryFactory.selectFrom(place);
        query = filterPlaces(query, placeFilterParam);
        query = applySort(query, placeFilterParam, sort);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize());
        return pagingUtil.getPageImpl(pageRequest, query);
    }


    @Override
    public Page<Place> findPickedPlacePage(PlaceFilterParam placeFilterParam, PagingParam pagingParam, CustomSort sort, Long userId) {
        JPQLQuery<Place> query = jpaQueryFactory
                .selectFrom(place)
                .where(place.placePicks.any().user.id.eq(userId));
        query = filterPlaces(query, placeFilterParam);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize());
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    private JPQLQuery<Place> filterPlaces(JPQLQuery<Place> query, PlaceFilterParam placeFilterParam) {
        if (placeFilterParam.getKeyword() != null) {
            query = query.where(place.name.contains(placeFilterParam.getKeyword()));
        }

        if (placeFilterParam.getCourseId() != null) {
            query = query.where(place.placeCourses.any().course.id.eq(placeFilterParam.getCourseId()));
        }

        if (placeFilterParam.getCategoryId() != null && !placeFilterParam.getCategoryId().isEmpty()) {
            query = query.where(place.placeCategories.any().category.id.in(placeFilterParam.getCategoryId()));
        }

        if (placeFilterParam.getDistance() != null) {
            NumberExpression<Double> distanceExpression = GeoQueryUtil.getDistanceExpression(
                    placeFilterParam.getLatitude(),
                    placeFilterParam.getLongitude(),
                    place.latitude,
                    place.longitude);
            query = query.where(distanceExpression.loe(placeFilterParam.getDistance()));
        }

        return query;
    }

    private JPQLQuery<Place> applySort(JPQLQuery<Place> query, PlaceFilterParam placeFilterParam, CustomSort sort) {
        if (sort == null) {
            sort = CustomSort.LATEST;
        }

        switch (sort) {
            case LATEST:
                query = query.orderBy(place.createdAt.desc());
            case DISTANCE:
                NumberExpression<Double> distanceExpression = GeoQueryUtil.getDistanceExpression(
                        placeFilterParam.getLatitude(),
                        placeFilterParam.getLongitude(),
                        place.latitude,
                        place.longitude);
                query = query.orderBy(distanceExpression.asc());
                break;
            case PICK:
                query = query.orderBy(place.placePicks.size().desc());
                break;
            case POPULAR:
                query = query.orderBy(place.viewCount.desc());  // TODO: 인기도 기준 재설정
            case RATING_ASC:
                query = query.orderBy(place.rating.asc());
                break;
            case RATING_DESC:
                query = query.orderBy(place.rating.desc());
                break;
        }

        return query;
    }
}