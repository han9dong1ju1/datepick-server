package app.hdj.datepick.domain.place.service;

import app.hdj.datepick.domain.photo.repository.PlaceReviewPhotoRepository;
import app.hdj.datepick.domain.pick.repository.PlacePickRepository;
import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import app.hdj.datepick.domain.review.dto.PlaceReviewDto;
import app.hdj.datepick.domain.review.repository.PlaceReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlacePickRepository placePickRepository;
    private final PlaceReviewRepository placeReviewRepository;
    private final PlaceReviewPhotoRepository placeReviewPhotoRepository;

    public Page<PlaceMetaDto> getPickedPlacePage(Pageable pageable) {

        //TODO Pick 한 User Id 가져오기
        Long userId = 10L;

        //Place Pick Relation 에서 User Id 로 Place 찾기
        List<Long> placeIds = placePickRepository.findPickedPlaceIds(userId);

        //Place id list로 Place meta 정보 가져오기
        return placeRepository.findPlaceMetaListById(placeIds, pageable);
    }

    public Page<PlaceMetaDto> getRecommendedPlaceList(Pageable pageable) {

        //TODO Recommend Place 기준만들어서 place id list 찾기
        List<Long> placeIds = new ArrayList<>();
        placeIds.add(10L);placeIds.add(11L);placeIds.add(12L);placeIds.add(14L);
        //Place id List로 Place meta 정보 가져오기
        return placeRepository.findPlaceMetaListById(placeIds, pageable);
    }

    public PlaceDetailDto getPlace(Long placeId) {

        //TODO User 기준 Picked Place인지 여부판단위해, User Id 가져오기
        Long userId = 10L;

        //User가 Place를 픽했는지 여부 T/F
        Boolean isPicked = placeRepository.isUserPickedPlace(placeId, userId);

        //Place의 Review 특정개수 가져오기
        List<PlaceReviewDto> placeReviews = placeReviewRepository.findConstReviewsWithPlaceId(placeId);

        //Place meta, isPicked, reviews를 조립
       return placeRepository.findPlaceDetail(placeId, isPicked, placeReviews);
    }

    public Place addPlace(PlaceRequestDto placeRequestDto) {

        //PlaceRequestDto -> Place mapping
        Place place = placeRequestDto.toPlace();

        //Place 새로 생성
        return placeRepository.save(place);
    }


    public Page<String> getPlaceImagePage(Long placeId, Pageable pageable) {

        //Place에 작성된 Review의 Image List를 가져온다.
        return placeReviewPhotoRepository.getPlaceReviewPhotoPage(placeId, pageable);
    }

}
