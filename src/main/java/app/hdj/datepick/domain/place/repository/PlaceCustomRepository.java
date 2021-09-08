package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.response.PlaceMetaDto;

public interface PlaceCustomRepository {
    PlaceMetaDto findByIdWithUserPicked(Long id);
}
