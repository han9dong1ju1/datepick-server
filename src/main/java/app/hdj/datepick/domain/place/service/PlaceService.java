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

    public PlaceDetailDto getPlace(Long id){
        Long userId = 1L;
        return placeRepository.findPlaceDetail(id, placeRepository.IsUserPickedPlace(id, userId), placeRepository.findReviewPhotoUrls(id));
    }

}
