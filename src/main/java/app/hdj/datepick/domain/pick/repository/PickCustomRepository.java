package app.hdj.datepick.domain.pick.repository;

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
     * @param userId 찾으려는 picked course의 user id
     * @return course의 id list
     */
    List<Long> findPickedCourseIds(Long userId);
}
