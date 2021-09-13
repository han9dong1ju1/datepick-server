package app.hdj.datepick.domain.place.service;

import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    // TODO: 파라미터 선정 및 구현
    public void getPlaceList() {}

    // TODO: 파라미터 선정 및 구현
    public void getPickedPlacePage() {}

    // TODO: 파라미터 선정 및 구현
    public void getRecommendedPlaceList() {}

    // TODO: 확인 및 수정
    public PlaceDetailDto getPlace(Long id) {
        Long userId = 1L;
        return placeRepository.findPlaceDetail(id, placeRepository.IsUserPickedPlace(id, userId), placeRepository.findReviewPhotoUrls(id));
    }

    // TODO: 파라미터 선정 및 구현
    public void getPlaceImagePage(Long id) {}

}
