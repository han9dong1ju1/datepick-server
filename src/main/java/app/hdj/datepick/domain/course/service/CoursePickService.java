package app.hdj.datepick.domain.course.service;

import app.hdj.datepick.domain.relation.repository.CoursePickRepository;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoursePickService {

    private final CoursePickRepository coursePickRepository;

    @Transactional
    public void addCoursePick(Long courseId, Long userId) {
        if (coursePickRepository.exists(courseId, userId)) {
            throw new CustomException(ErrorCode.PICK_ALREADY_EXISTS);
        }
        coursePickRepository.save(courseId, userId);
    }

    @Transactional
    public void removeCoursePick(Long courseId, Long userId) {
        if (!coursePickRepository.exists(courseId, userId)) {
            throw new CustomException(ErrorCode.PICK_NOT_EXISTS);
        }
        coursePickRepository.remove(courseId, userId);
    }

}
