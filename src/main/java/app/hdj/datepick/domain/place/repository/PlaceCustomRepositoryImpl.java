package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.hdj.datepick.domain.place.entity.QPlace.place;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public Page<Place> findPlacePage(PlaceFilterParam placeFilterParam, Pageable pageable) {
        JPQLQuery<Place> query = filterPlaces(placeFilterParam);

        return pagingUtil.getPageImpl(pageable, query);
    }

    private JPQLQuery<Place> filterPlaces(PlaceFilterParam placeFilterParam) {
        JPQLQuery<Place> query = jpaQueryFactory
                .selectFrom(place);

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
        return query;   // TODO : distance filtering
    }

}