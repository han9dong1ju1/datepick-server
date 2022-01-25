package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.PlaceWithOrderDto;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.diary.dto.PlaceReviewDto;
import app.hdj.datepick.global.common.dto.GeoPointDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceCustomRepository {
//
//
//    /**
//     *
//     * @param placeIds 찾으려는 place id lists
//     * @param pageable paging 정보
//     * @return Place Meta List를 Paging 규격에 맞게 제공
//     */
//    Page<PlaceMetaDto> findPlaceMetaPageById(List<Long> placeIds, Pageable pageable);
//
//    /**
//     *
//     * @param placeIds 찾으려는 place id lists
//     * @return Place Meta List
//     */
//    List<PlaceMetaDto> findPlaceMetaListById(List<Long> placeIds);
//
//    /**
//     *
//     * @param placeId  Place meta정보 찾기
//     * @param isPicked 호출한 user가 place를 picked 여부
//     * @param reviews Place에 연관된 review, photoUrls 정보 list
//     * @return place id 에 맞는 PlaceDetail 정보
//     */
//    PlaceDetailDto findPlaceDetail(Long placeId, Boolean isPicked, List<PlaceReviewDto> reviews);
//
//
//    List<Place> findPlacesByIdOrderByIdAsc(List<Long> placeId);
//
//    Page<PlaceMetaDto> findPlaceMetaPageByGeoPoint(GeoPointDto geopointDto, Pageable pageable);
//
//    List<PlaceWithOrderDto> findOrderAndPlaceInCourse(Long courseId);

}
