package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.QPlaceDetailDto;
import app.hdj.datepick.domain.place.dto.QPlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.QPlaceWithOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static app.hdj.datepick.domain.place.entity.QPlace.place;
import static app.hdj.datepick.domain.relation.entity.QCoursePlaceRelation.coursePlaceRelation;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

//    private final JPAQueryFactory jpaQueryFactory;
//
//    /**
//     *
//     * @param placeId 찾을 place id
//     * @param isPicked 요청한 user가 타깃 place id를 픽했는지 여부
//     * @param reviews place에 작성된 review list
//     * @return 결과를 조합한 PlaceDetailDto instance
//     */
//    @Override
//    public PlaceDetailDto findPlaceDetail(Long placeId, Boolean isPicked, List<PlaceReviewDto> reviews){
//        //TODO 결과가 2개 이상이면 NonUniqueResultException 발생
//        PlaceDetailDto placeDetailDto = jpaQueryFactory
//                .select(new QPlaceDetailDto(
//                        place.id,
//                        place.kakaoId,
//                        place.name,
//                        place.rating,
//                        place.address,
//                        place.latitude,
//                        place.longitude,
//                        place.type,
//                        place.subtype,
//                        place.category,
//                        Expressions.constant(isPicked),
//                        Expressions.constant(reviews)
//                ))
//                .from(place)
//                .where(place.id.eq(placeId))
//                .fetchOne();
//
//        if (placeDetailDto == null){
//            //TODO exception 아이디에 맞는 장소가 없음.
//        }
//        return placeDetailDto;
//    }
//
//    @Override
//    public Page<PlaceMetaDto> findPlaceMetaPageById(List<Long> placeIds, Pageable pageable) {
//
//        JPAQuery<PlaceMetaDto> query = jpaQueryFactory
//                .select(
//                        new QPlaceMetaDto(
//                                place.id,
//                                place.kakaoId,
//                                place.name,
//                                place.rating,
//                                place.address,
//                                place.latitude,
//                                place.longitude
//                        )
//                )
//                .from(place)
//                .where(place.id.in(placeIds))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());
//        //정렬 적용
//        for (Sort.Order o : pageable.getSort()) {
//            PathBuilder pathBuilder = new PathBuilder(place.getType(), place.getMetadata());
//            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
//        }
//
//        QueryResults<PlaceMetaDto> results = query.fetchResults();
//
//        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
//    }
//
//    @Override
//    public List<PlaceMetaDto> findPlaceMetaListById(List<Long> placeIds) {
//        return jpaQueryFactory
//                .select(
//                        new QPlaceMetaDto(
//                                place.id,
//                                place.kakaoId,
//                                place.name,
//                                place.rating,
//                                place.address,
//                                place.latitude,
//                                place.longitude
//                        )
//                )
//                .from(place)
//                .where(place.id.in(placeIds))
//                .fetch();
//    }
//
//    @Override
//    public List<Place> findPlacesByIdOrderByIdAsc(List<Long> placeId) {
//        return jpaQueryFactory
//                .selectFrom(place)
//                .where(place.id.in(placeId))
//                .orderBy(place.id.asc())
//                .fetch();
//    }
//
//    @Override
//    public Page<PlaceMetaDto> findPlaceMetaPageByGeoPoint(GeoPointDto geopointDto, Pageable pageable) {
//        NumberExpression<Double> lat = Expressions.asNumber(geopointDto.getLatitude());
//        NumberExpression<Double> lng = Expressions.asNumber(geopointDto.getLongitude());
//        NumberExpression<Double> formula =
//                power(
//                    lat.subtract(place.latitude.castToNum(Double.class)), 2)
//                .multiply(4637.61)
//                .add(
//                power(
//                    lng.subtract(place.longitude.castToNum(Double.class)), 2)
//                .multiply(2819.61)
//                ).sqrt().multiply(1.609);
//
//
//
//        JPAQuery<PlaceMetaDto> query = jpaQueryFactory
//                .select(
//                        new QPlaceMetaDto(
//                                place.id,
//                                place.kakaoId,
//                                place.name,
//                                place.rating,
//                                place.address,
//                                place.latitude,
//                                place.longitude
//                        )
//                )
//                .from(place)
//                .where(formula.lt(geopointDto.getDistanceLimit()))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());
//
//        //정렬 적용
//        for (Sort.Order o : pageable.getSort()) {
//            PathBuilder pathBuilder = new PathBuilder(place.getType(), place.getMetadata());
//            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
//        }
//
//        QueryResults<PlaceMetaDto> results = query.fetchResults();
//
//        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
//    }
//
//    @Override
//    public List<PlaceWithOrderDto> findOrderAndPlaceInCourse(Long courseId) {
//
//        return jpaQueryFactory.select(new QPlaceWithOrderDto(
//                        coursePlaceRelation.course.id,
//                        coursePlaceRelation.placeOrder,
//                        coursePlaceRelation.place
//        ))
//                .from(coursePlaceRelation)
//                .innerJoin(coursePlaceRelation.place, place)
//                .where(coursePlaceRelation.course.id.eq(courseId))
//                .fetch();
//    }
}

