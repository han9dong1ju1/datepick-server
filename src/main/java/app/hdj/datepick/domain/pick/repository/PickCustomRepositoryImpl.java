package app.hdj.datepick.domain.pick.repository;

import app.hdj.datepick.domain.pick.entity.QCoursePick;
import app.hdj.datepick.domain.pick.entity.QPlacePick;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static app.hdj.datepick.domain.pick.entity.QCoursePick.coursePick;
import static app.hdj.datepick.domain.pick.entity.QPlacePick.placePick;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PickCustomRepositoryImpl implements PickCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Long> findPickedPlaceIds(Long userId) {
        return jpaQueryFactory
                .select(placePick.place.id)
                .from(placePick)
                .where(placePick.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<Long> findPickedCourseIds(Long userId) {
        return jpaQueryFactory
                .select(coursePick.course.id)
                .from(coursePick)
                .where(coursePick.user.id.eq(userId))
                .fetch();
    }
}
