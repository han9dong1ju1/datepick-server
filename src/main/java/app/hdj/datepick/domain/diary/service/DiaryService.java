package app.hdj.datepick.domain.diary.service;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.course.repository.CourseRepository;
import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.dto.DiaryRequest;
import app.hdj.datepick.domain.diary.dto.DiaryResponse;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.diary.repository.DiaryRepository;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.domain.relation.repository.CoursePlaceRepository;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.config.file.FileService;
import app.hdj.datepick.global.enums.CustomSort;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final CourseRepository courseRepository;
    private final CoursePlaceRepository coursePlaceRepository;
    private final FileService fileService;

    @PersistenceContext
    private final EntityManager em;

    public CustomPage<DiaryResponse> getDiaryPage(PagingParam pagingParam,
                                                  CustomSort customSort,
                                                  DiaryFilterParam diaryFilterParam,
                                                  Long userId) {
        Sort sort = CustomSort.toSort(customSort, CustomSort.LATEST);
        Page<Diary> diaryPage =  diaryRepository.findDiaryPage(diaryFilterParam, pagingParam, sort, userId);
        return new CustomPage<>(
                diaryPage.getTotalElements(),
                diaryPage.getTotalPages(),
                diaryPage.getNumber(),
                diaryPage.getContent().stream()
                        .map(DiaryResponse::from)
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public DiaryResponse addDiary(DiaryRequest diaryRequest,
                                  Long userId) {

        Course course = courseRepository.findById(diaryRequest.getCourseId()).orElseThrow();

        //course 에 요청한 place 가 존재하지 않으면 reject
        CoursePlaceRelation coursePlaceRelation =  coursePlaceRepository.findByCourseIdAndPlaceId(diaryRequest.getCourseId(), diaryRequest.getPlaceId()).orElseThrow();

        //course 가 본인 course 가 아니면 reject
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        Diary diary = Diary.builder()
                .content(diaryRequest.getContent())
                .rating(diaryRequest.getRating())
                .coursePlaceRelation(coursePlaceRelation)
                .build();
        diary = diaryRepository.save(diary);
        em.refresh(diary);

        return DiaryResponse.from(diary);

    }

    public DiaryResponse getDiary(Long diaryId, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        Course course = diary.getCoursePlaceRelation().getCourse();

        if (!course.getUser().getId().equals(userId) && course.getIsPrivate()) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        return DiaryResponse.from(diary);
    }

    @Transactional
    public DiaryResponse modifyDiary(Long diaryId,
                                     DiaryRequest diaryRequest,
                                     Long userId ) {

        Course course = courseRepository.findById(diaryRequest.getCourseId()).orElseThrow();

        //course 에 요청한 place 가 존재하지 않으면 reject
        if (coursePlaceRepository.existsByCourseIdAndPlaceId(diaryRequest.getCourseId(), diaryRequest.getPlaceId())) {
            throw new CustomException(ErrorCode.ENTITY_NOT_FOUND);
        }
        //course 가 본인 course 가 아니면 reject
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        diary.setContent(diaryRequest.getContent());
        diary.setRating(diaryRequest.getRating());

        return DiaryResponse.from(diary);
    }

    public void removeDiary(Long diaryId, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        Course course = diary.getCoursePlaceRelation().getCourse();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }
        diaryRepository.delete(diary);
    }

    public ImageUrl addDiaryImage(Long diaryId, MultipartFile image, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();

        // 다이어라가 속한 코스가 본인게 아니라면 reject
        if (!diary.getCoursePlaceRelation().getCourse().getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }
        String imageUrl = fileService.add(image, "diary/" + diaryId);
        return new ImageUrl(imageUrl);
    }

    public void removeDiaryImage(Long diaryId, String imageUrl, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        if (!diary.getCoursePlaceRelation().getCourse().getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }
        fileService.remove(imageUrl);
    }

}
