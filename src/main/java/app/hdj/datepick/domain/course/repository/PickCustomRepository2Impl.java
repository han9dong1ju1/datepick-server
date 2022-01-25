package app.hdj.datepick.domain.course.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.hdj.datepick.domain.course.entity.QCoursePick.coursePick;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PickCustomRepository2Impl implements PickCustomRepository2 {

//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<Long> findPickedCourseIds(Long userId) {
//        return jpaQueryFactory
//                .select(coursePick.course.id)
//                .from(coursePick)
//                .where(coursePick.user.id.eq(userId))
//                .fetch();
//    }
//
//    @Override
//    public Boolean isUserPickedCourse(Long courseId, Long userId) {
//        Long isExistUserId = jpaQueryFactory
//                .select(coursePick.user.id)
//                .from(coursePick)
//                .where(coursePick.user.id.eq(userId), coursePick.course.id.eq(courseId))
//                .fetchFirst();
//        Boolean isPicked = true;
//        if (isExistUserId == null){
//            isPicked = false;
//        }
//        return isPicked;
//    }
}
