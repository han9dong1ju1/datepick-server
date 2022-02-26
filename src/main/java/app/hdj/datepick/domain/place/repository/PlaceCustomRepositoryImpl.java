package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceDto;
import app.hdj.datepick.domain.place.dto.QCategoryDto;
import app.hdj.datepick.domain.place.dto.QPlaceDto;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
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

import java.util.HashSet;
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


        Page<Long> placeIdPage = filteringPlaces(courseId, placeFilterParam, pageable);

        return fetchPlaces(placeIdPage, pageable);
    }

    private Page<PlaceDto> fetchPlaces(Page<Long> placeIdPage, Pageable pageable) {

        JPAQuery<?> query = jpaQueryFactory
                .from(placeCategoryRelation)
                .innerJoin(placeCategoryRelation.place, place)
                .innerJoin(placeCategoryRelation.category, category)
                .where(placeCategoryRelation.place.id.in(placeIdPage.getContent()));

        JPQLQuery<?> sortedQuery = pagingUtil.applySort(pageable, query, Place.class);

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

        //course 필터링
        HashSet<Long> resultIdSet = coursefiltering(courseId, new HashSet<>());

        //keyword, distance 필터링
        resultIdSet = keywordAndDistanceFiltering(placeFilterParam.getKeyword(), placeFilterParam.getDistance(), placeFilterParam.getLatitude(), placeFilterParam.getLongitude(), resultIdSet);

        //category 필터링
        resultIdSet = categoryFiltering(placeFilterParam.getCategory(), resultIdSet);

        //paging 처리
        JPAQuery<Long> placeQuery = jpaQueryFactory.select(place.id).from(place).where(place.id.in(resultIdSet));

        return pagingUtil.getPageImpl(pageable, placeQuery, Place.class);
    }

    private HashSet<Long> coursefiltering(Long courseId, HashSet<Long> prevSet){
        if (courseId != null) {
            HashSet<Long> resultSet =  new HashSet<>(jpaQueryFactory
                    .select(coursePlaceRelation.place.id)
                    .from(coursePlaceRelation)
                    .where(coursePlaceRelation.course.id.eq(courseId))
                    .fetch()
            );
            if (prevSet.size() > 0){
                resultSet.retainAll(prevSet);
            }
            return resultSet;
        }
        return prevSet;

    }

    private HashSet<Long> keywordAndDistanceFiltering(String keyword, Double distance, Double latitude, Double longitude, HashSet<Long> prevSet) {
        if (keyword == null && distance == null) {
            return prevSet;
        }
        else {
            JPAQuery<Long> query = jpaQueryFactory.select(place.id).from(place);
            if (keyword != null) {
                NumberTemplate<Double> booleanTemplate = Expressions.numberTemplate(Double.class, "function('match', {0}, {1})", place.name, "+" + keyword + "*");
                query.where(booleanTemplate.gt(0));
            }
            if (distance != null) {
                //TODO : distance filtering

            }

            HashSet<Long> resultSet = new HashSet<Long>(query.fetch());
            if (prevSet.size() > 0){
                resultSet.retainAll(prevSet);
            }
            return resultSet;
        }
    }

    private HashSet<Long> categoryFiltering(String categoryName, HashSet<Long> prevSet) {
        if (categoryName != null){
            HashSet<Long> resultSet = new HashSet<Long>(jpaQueryFactory.select(placeCategoryRelation.place.id)
                    .from(placeCategoryRelation)
                    .innerJoin(placeCategoryRelation.category, category)
                    .where(placeCategoryRelation.category.name.eq(categoryName))
                    .fetch());
            if (prevSet.size() > 0) {
                resultSet.retainAll(prevSet);
            }
            return resultSet;
        }
        return prevSet;
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