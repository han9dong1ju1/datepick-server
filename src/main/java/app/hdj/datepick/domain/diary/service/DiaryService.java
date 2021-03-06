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
import app.hdj.datepick.global.common.ImageUrlResponse;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.config.file.FileService;
import app.hdj.datepick.global.enums.CustomSort;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final CourseRepository courseRepository;
    private final CoursePlaceRepository coursePlaceRepository;
    private final FileService fileService;

    public CustomPage<DiaryResponse> getDiaryPage(
        PagingParam pagingParam, CustomSort customSort, DiaryFilterParam diaryFilterParam
    ) {
        Page<Diary> diaryPage = diaryRepository.findDiaryPage(diaryFilterParam,
                                                              pagingParam,
                                                              customSort);
        return new CustomPage<>(diaryPage.getTotalElements(),
                                diaryPage.getTotalPages(),
                                diaryPage.getNumber(),
                                diaryPage.getContent()
                                    .stream()
                                    .map(DiaryResponse::from)
                                    .collect(Collectors.toList()));
    }

    public CustomPage<DiaryResponse> getMyDiaryPage(
        PagingParam pagingParam, CustomSort customSort, DiaryFilterParam diaryFilterParam
    ) {
        Page<Diary> diaryPage = diaryRepository.findMyDiaryPage(diaryFilterParam,
                                                                pagingParam,
                                                                customSort);
        return new CustomPage<>(diaryPage.getTotalElements(),
                                diaryPage.getTotalPages(),
                                diaryPage.getNumber(),
                                diaryPage.getContent()
                                    .stream()
                                    .map(DiaryResponse::from)
                                    .collect(Collectors.toList()));
    }


    @Transactional
    public DiaryResponse addDiary(
        DiaryRequest diaryRequest, Long userId
    ) {
        //course ??? ????????? place ??? ???????????? ????????? reject
        CoursePlaceRelation coursePlace = coursePlaceRepository.findByCourseIdAndPlaceId(
            diaryRequest.getCourseId(),
            diaryRequest.getPlaceId()).orElseThrow();

        //course ??? ?????? course ??? ????????? reject
        Course course = courseRepository.findById(diaryRequest.getCourseId()).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        Diary diary = Diary.builder()
            .content(diaryRequest.getContent())
            .rating(diaryRequest.getRating())
            .coursePlace(coursePlace)
            .build();
        diary = diaryRepository.save(diary);

        return DiaryResponse.from(diary);
    }

    public DiaryResponse getDiary(Long diaryId, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        Course course = diary.getCoursePlace().getCourse();

        if (!course.getUser().getId().equals(userId) && course.getIsPrivate()) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        return DiaryResponse.from(diary);
    }

    @Transactional
    public DiaryResponse modifyDiary(
        Long diaryId, DiaryRequest diaryRequest, Long userId
    ) {
        //course ??? ????????? place ??? ???????????? ????????? reject
        if (coursePlaceRepository.existsByCourseIdAndPlaceId(diaryRequest.getCourseId(),
                                                             diaryRequest.getPlaceId())) {
            throw new CustomException(ErrorCode.ENTITY_NOT_FOUND);
        }

        //course ??? ?????? course ??? ????????? reject
        Course course = courseRepository.findById(diaryRequest.getCourseId()).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        diary.setContent(diaryRequest.getContent());
        diary.setRating(diaryRequest.getRating());

        return DiaryResponse.from(diary);
    }

    @Transactional
    public void removeDiary(Long diaryId, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        Course course = diary.getCoursePlace().getCourse();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }
        diaryRepository.delete(diary);
    }

    @Transactional
    public ImageUrlResponse addDiaryImage(Long diaryId, MultipartFile image, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();

        // ??????????????? ?????? ????????? ????????? ???????????? reject
        if (!diary.getCoursePlace().getCourse().getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }
        String imageUrl = fileService.add(image, "diary/" + diaryId);
        return new ImageUrlResponse(imageUrl);
    }

    @Transactional
    public void removeDiaryImage(Long diaryId, String imageUrl, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        if (!diary.getCoursePlace().getCourse().getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }
        fileService.remove(imageUrl);
    }
}
