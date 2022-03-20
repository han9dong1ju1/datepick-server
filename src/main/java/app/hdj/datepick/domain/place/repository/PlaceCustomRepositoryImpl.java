package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.dto.PlaceResponse;
import app.hdj.datepick.domain.place.entity.Place;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static app.hdj.datepick.domain.place.entity.QPlace.place;
import static app.hdj.datepick.domain.place.entity.QPlacePick.placePick;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;
    private final GeoQueryUtil geoQueryUtil;

    @Override
    public Page<PlaceResponse> findPlacePage(PlaceFilterParam placeFilterParam, Pageable pageable, CustomSort customSort, Boolean onlyPicked) {

        Long userId = 222L;

        JPQLQuery<Tuple> query = applySort(filterPlaces(userId, placeFilterParam, onlyPicked), customSort);
        Page<Tuple> tuplePage = pagingUtil.getPageImpl(pageable, query);
        return mapToPlaceResponse(tuplePage);
    }

    private JPQLQuery<Tuple> filterPlaces(Long userId, PlaceFilterParam placeFilterParam, Boolean onlyPicked) {
        JPQLQuery<Tuple> query = jpaQueryFactory
                    .select(place, Expressions.anyOf(placePick.user.id.eq(userId)))
                    .from(place)
                    .leftJoin(placePick).on(place.id.eq(placePick.place.id))
                    .groupBy(place.id);
        System.out.println(onlyPicked);
        if (onlyPicked) {
            query.where(Expressions.anyOf(placePick.user.id.eq(userId)));
        }

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
        return query.where(place.placeCourses.any().course.id.eq(courseId));
    }

    private <T> JPQLQuery<T> filterKeyword(String keyword, JPQLQuery<T> query) {
        return query.where(place.name.contains(keyword));
    }

    private <T> JPQLQuery<T> filterCategories(List<Long> categoryId, JPQLQuery<T> query) {
        return query.where(place.placeCategories.any().category.id.in(categoryId));
    }

    private <T> JPQLQuery<T> filterDistance(Double distance, Double latitude, Double longitude, JPQLQuery<T> query) {

        NumberExpression<Double> distanceExpression = geoQueryUtil.getDistanceExpression(latitude, longitude);
        return query.where(distanceExpression.loe(distance)).orderBy(distanceExpression.asc());
    }

    private JPQLQuery<Tuple> applySort(JPQLQuery<Tuple> query, CustomSort customSort) {
        OrderSpecifier orderSpecifier = CustomSort.toPlaceSort(customSort);
        if (orderSpecifier == null) {
            return query;
        } else {
            return query.orderBy(orderSpecifier);
        }
    }

    private Page<PlaceResponse> mapToPlaceResponse(Page<Tuple> tuplePage) {

        List<Tuple> tupleContents = tuplePage.getContent();

        List<Place> placeContents = tupleContents.stream().map(tuple -> {
            Object[] objects = tuple.toArray();
            Place place = (Place) objects[0];
            Boolean isPicked = (Boolean) objects[1];
            place.setIsPicked(isPicked);
            return place;
        }).collect(Collectors.toList());

        List<PlaceResponse> placeResponseContents =placeContents.stream().map(place -> {
            return new PlaceResponse(place);
        }).collect(Collectors.toList());

        return new PageImpl<>(placeResponseContents, tuplePage.getPageable(), tuplePage.getTotalElements());
    }


}