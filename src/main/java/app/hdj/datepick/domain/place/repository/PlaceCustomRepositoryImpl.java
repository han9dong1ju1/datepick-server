package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceDto;
import app.hdj.datepick.domain.place.dto.QCategoryDto;
import app.hdj.datepick.domain.place.dto.QPlaceDto;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.hdj.datepick.domain.place.entity.QCategory.category;
import static app.hdj.datepick.domain.place.entity.QPlace.place;
import static app.hdj.datepick.domain.relation.entity.QCoursePlaceRelation.coursePlaceRelation;
import static app.hdj.datepick.domain.relation.entity.QPlaceCategoryRelation.placeCategoryRelation;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Page<PlaceDto> findPlacePage(Long courseId, PlaceFilterParam placeFilterParam, Pageable pageable) {

        Page<Long> filteredIds = filteringPlaces(courseId, placeFilterParam, pageable);

        return fetchPlaces(filteredIds, pageable);
    }




    private Page<PlaceDto> fetchPlaces(Page<Long> placeIdPage, Pageable pageable) {

        JPAQuery<?> query = jpaQueryFactory
                .from(placeCategoryRelation)
                .join(place).on(place.id.eq(placeCategoryRelation.place.id))
                .join(category).on(category.id.eq(placeCategoryRelation.category.id))
                .where(placeCategoryRelation.place.id.in(placeIdPage.getContent()));

        JPQLQuery<?> sortedQuery = pagingUtil.applySorting(pageable.getSort(), query, Place.class);

        List<PlaceDto> results = sortedQuery.transform(
                groupBy(
                        placeCategoryRelation.place.id
                ).list(new QPlaceDto(
                        placeCategoryRelation.place,
                        list(new QCategoryDto(
                                placeCategoryRelation.category.id,
                                placeCategoryRelation.category.name
                        ))
                ))
        );

        return new PageImpl<PlaceDto>(results, pageable, placeIdPage.getTotalElements());
    }

    private Page<Long> filteringPlaces(Long courseId, PlaceFilterParam placeFilterParam, Pageable pageable) {

        JPQLQuery<Long> query = jpaQueryFactory
                .select(place.id)
                .distinct()
                .from(place);

        //course 필터링
        query = coursefiltering(courseId, query);

        //keyword 필터링
        query = keywordFiltering(placeFilterParam.getKeyword(), query);

        //distance 필터링
        query = distanceFiltering(
                placeFilterParam.getDistance(),
                placeFilterParam.getLatitude(),
                placeFilterParam.getLongitude(),
                query);

        //category 필터링
        query = categoryFiltering(placeFilterParam.getCategory(), query);

        return pagingUtil.getPageImpl(pageable, query, Place.class);
    }

    private <T> JPQLQuery<T> coursefiltering(Long courseId, JPQLQuery<T> query){
        if (courseId != null){
            query.join(coursePlaceRelation).on(coursePlaceRelation.place.id.eq(place.id))
                    .where(coursePlaceRelation.course.id.eq(courseId));
        }
        return query;
    }

    private <T> JPQLQuery<T> keywordFiltering(String keyword, JPQLQuery<T> query) {
        if (keyword != null){
            //NumberTemplate<Integer> booleanTemplate = Expressions.numberTemplate(Integer.class, "function('match', {0}, {1})", place.name, "+떡볶이*");
            //query.where(booleanTemplate.gt(0));
            query.where(place.name.contains(keyword));
        }
        return query;
    }

    private <T> JPQLQuery<T> distanceFiltering(Double distance, Double latitude, Double longitude, JPQLQuery<T> query) {
        if (distance != null){
            //TODO : distance filtering
        }
        return query;
    }

    private <T> JPQLQuery<T> categoryFiltering(String categoryName, JPQLQuery<T> query) {
        if (categoryName != null){
            query.join(placeCategoryRelation).on(placeCategoryRelation.place.id.eq(place.id))
                    .join(category).on(category.id.eq(placeCategoryRelation.category.id))
                    .where(category.name.eq(categoryName));
        }
        return query;
    }

    @Override
    public PlaceDto findPlace(Long placeId) {

        List<PlaceDto> placeDto = jpaQueryFactory
                .from(placeCategoryRelation)
                .innerJoin(placeCategoryRelation.place, place)
                .innerJoin(placeCategoryRelation.category, category)
                .where(placeCategoryRelation.place.id.eq(placeId))
                .transform(
                        groupBy(
                                placeCategoryRelation.place.id
                        ).list(new QPlaceDto(
                                placeCategoryRelation.place,
                                list(new QCategoryDto(
                                        placeCategoryRelation.category.id,
                                        placeCategoryRelation.category.name
                                ))
                        ))
                );
        //TODO: redis 처리

        if (placeDto.size() < 1) {
            throw new CustomException(ErrorCode.ENTITY_NOT_FOUND);
        }
        return placeDto.get(0);
    }

}