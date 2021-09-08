package app.hdj.datepick.domain.place.service;

import app.hdj.datepick.domain.place.dto.response.PlaceMetaDto;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceMetaDto getPlace(Long id){
        return placeRepository.findByIdWithUserPicked(id);
    }

}
