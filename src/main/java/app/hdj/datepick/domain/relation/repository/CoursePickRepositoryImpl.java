package app.hdj.datepick.domain.relation.repository;

import static app.hdj.datepick.domain.course.entity.QCoursePick.coursePick;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.course.entity.CoursePick;
import app.hdj.datepick.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CoursePickRepositoryImpl implements CoursePickRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean exists(Long courseId, Long userId) {
        Integer fetchOne = jpaQueryFactory.selectOne().from(coursePick)
            .where(coursePick.user.id.eq(userId)).where(coursePick.course.id.eq(courseId))
            .fetchFirst();
        return fetchOne != null;
    }

    @Override
    public void save(Long courseId, Long userId) {
        Course course = Course.builder().id(courseId).build();
        User user = User.builder().id(userId).build();
        CoursePick coursePick = CoursePick.builder().course(course).user(user).build();
        em.persist(coursePick);
    }

    @Override
    public void remove(Long courseId, Long userId) {
        jpaQueryFactory.delete(coursePick).where(coursePick.user.id.eq(userId))
            .where(coursePick.course.id.eq(courseId)).execute();
    }
}
