package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.dto.PlaceResponse;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.relation.entity.PlaceCategoryRelation;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import app.hdj.datepick.global.util.GeoQueryUtil;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static app.hdj.datepick.domain.place.entity.QPlace.place;
import static app.hdj.datepick.domain.place.entity.QPlacePick.placePick;
import static app.hdj.datepick.domain.relation.entity.QPlaceCategoryRelation.placeCategoryRelation;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;
    private final GeoQueryUtil geoQueryUtil;

    @Override
    public Page<Place> findPlacePage(PlaceFilterParam placeFilterParam, PagingParam pagingParam, Sort sort) {

        JPQLQuery<Place> query = jpaQueryFactory
                .selectFrom(place);
        query = filterPlaces(query, placeFilterParam);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(), placeFilterParam.getSort(sort));
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    private JPQLQuery<Place> filterPlaces(JPQLQuery<Place> query, PlaceFilterParam placeFilterParam) {

        if (placeFilterParam.getKeyword() != null) {
            query = filterKeyword(placeFilterParam.getKeyword(), query);
        }

        if (placeFilterParam.getCourseId() != null) {
            query = filterCourse(placeFilterParam.getCourseId(), query);
        }

        if (placeFilterParam.getCategoryId() != null && !placeFilterParam.getCategoryId().isEmpty()) {
            query = filterCategories(placeFilterParam.getCategoryId(), query);
        }

        if (placeFilterParam.getDistance() != null) {

            query = filterDistance(placeFilterParam.getDistance(),
                    placeFilterParam.getLatitude(),
                    placeFilterParam.getLongitude(),
                    query);
        }
        return query;
    }

    private <T> JPQLQuery<T> filterCourse(Long courseId, JPQLQuery<T> query) {
        return query.where(place.coursePlaceRelations.any().course.id.eq(courseId));
    }

    private <T> JPQLQuery<T> filterKeyword(String keyword, JPQLQuery<T> query) {
        return query.where(place.name.contains(keyword));
    }

    private <T> JPQLQuery<T> filterCategories(List<Long> categoryId, JPQLQuery<T> query) {
        return query.where(place.categoryRelations.any().category.id.in(categoryId));
    }

    private <T> JPQLQuery<T> filterDistance(Double distance, Double latitude, Double longitude, JPQLQuery<T> query) {

        NumberExpression<Double> distanceExpression = geoQueryUtil.getDistanceExpression(latitude, longitude);
        return query.where(distanceExpression.loe(distance)).orderBy(distanceExpression.asc());
    }

}