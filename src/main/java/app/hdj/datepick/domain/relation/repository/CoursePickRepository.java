package app.hdj.datepick.domain.relation.repository;

public interface CoursePickRepository {

    boolean exists(Long courseId, Long userId);
    void save(Long courseId, Long userId);
    void remove(Long courseId, Long userId);

}
