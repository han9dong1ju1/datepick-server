package app.hdj.datepick.domain.search.service;

import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import app.hdj.datepick.domain.search.dto.GeoPointDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final PlaceRepository placeRepository;

    public Page<PlaceMetaDto> geoSearchPlace(GeoPointDto geopointDto, Pageable pageable) {
        Double distanceLimit = 0.1;  // 검색 반경 제한 (KM)
        return placeRepository.findPlaceMetaPageByGeoPoint(geopointDto, pageable);
    }

}
