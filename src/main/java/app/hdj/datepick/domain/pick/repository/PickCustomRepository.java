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


    /**
     *
     * @param placeId pick여부 확일할 place
     * @param userId pick 여부 기준 user
     * @return pick 여부 T/F
     */
    Boolean isUserPickedPlace(Long placeId ,Long userId);

    /**
     *
     * @param courseId pick여부 확일할 course
     * @param userId pick 여부 기준 user
     * @return pick 여부 T/F
     */
    Boolean isUserPickedCourse(Long courseId ,Long userId);
}
