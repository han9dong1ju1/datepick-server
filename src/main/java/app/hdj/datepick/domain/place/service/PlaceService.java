package app.hdj.datepick.domain.place.service;

import app.hdj.datepick.domain.place.dto.PlaceDto;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;


    public PlaceDto getPlaceById(Long id){
        return placeRepository.findById(id, PlaceDto.class).orElseThrow();
    }

}
