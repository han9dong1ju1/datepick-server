package app.hdj.datepick.domain.place.repository;

import java.util.List;

public interface PickCustomRepository {
    /**
     *
     * @param userId 찾으려는 picked place의 user id
     * @return place의 id list
     */
    List<Long> findPickedPlaceIds(Long userId);


    /**
     *
     * @param placeId pick여부 확일할 place
     * @param userId pick 여부 기준 user
     * @return pick 여부 T/F
     */
    Boolean isUserPickedPlace(Long placeId ,Long userId);
}
