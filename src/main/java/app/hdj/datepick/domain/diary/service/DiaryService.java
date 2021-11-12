package app.hdj.datepick.domain.diary.service;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.course.repository.CourseRepository;
import app.hdj.datepick.domain.diary.dto.DiaryDetailDto;
import app.hdj.datepick.domain.diary.dto.DiaryMetaDto;
import app.hdj.datepick.domain.diary.dto.DiaryModifyRequestDto;
import app.hdj.datepick.domain.diary.dto.ModifyDiaryDto;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.diary.repository.DiaryRepository;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import app.hdj.datepick.domain.review.dto.ModifyPlaceReviewDto;
import app.hdj.datepick.domain.review.dto.PlaceReviewDto;
import app.hdj.datepick.domain.review.entity.PlaceReview;
import app.hdj.datepick.domain.review.repository.PlaceReviewRepository;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.global.common.enums.Style;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final CourseRepository courseRepository;
    private final PlaceReviewRepository placeReviewRepository;


    public Page<DiaryMetaDto> getMyDiaryPage(Pageable pageable) {
        return diaryRepository.findMyDiariesPage(pageable);
    }


    public void getMyDiaryDateList() {

    }

    public DiaryDetailDto getDiary(Long diaryId) {
        List<PlaceReviewDto> placeReviews = placeReviewRepository.findPlaceReviewDtoByDiaryId(diaryId);
        return diaryRepository.findDiaryDetail(diaryId, placeReviews);
    }


    @Transactional
    public DiaryDetailDto addDiary(DiaryModifyRequestDto diaryModifyRequestDto) {

        ModifyDiaryDto diaryDto = diaryModifyRequestDto.getDiary();
        List<ModifyPlaceReviewDto> placeReviewDtos = diaryModifyRequestDto.getPlaceReviews();

        //User 받아오기
        Long userId = 12L; //TODO user id
        User user = userRepository.findById(userId).orElseThrow(); //TODO exception

        //Course 받아오기
        Long courseId = diaryDto.getCourseId();
        Course course = courseRepository.findById(courseId).orElseThrow(); //TODO exception


        //다이어리 생성
        Diary diary = Diary.builder()
                .course(course)
                .user(user)
                .title(diaryDto.getTitle())
                .likeCount(0)
                .style(Style.findByString(diaryDto.getStyle()))
                .build();
        diary = diaryRepository.save(diary);

        //Place Reviews 생성
        //place 생성
        List<Place> places = placeRepository.findPlacesByIdOrderByIdAsc(
                placeReviewDtos.stream().map(ModifyPlaceReviewDto::getPlaceId).collect(Collectors.toList())
        );
        List<PlaceReview> placeReviews = new ArrayList<>();

        for (int idx = 0; idx < placeReviewDtos.stream().count(); idx++){
            ModifyPlaceReviewDto modifyPlaceReviewDto = placeReviewDtos.get(idx);
            Place place = places.get(idx);

            PlaceReview placeReview = PlaceReview.builder()
                    .diary(diary)
                    .placeOrder(modifyPlaceReviewDto.getPlaceOrder())
                    .rating(modifyPlaceReviewDto.getRating())
                    .content(modifyPlaceReviewDto.getContent())
                    .place(place)
                    .user(user)
                    .build();
            placeReviews.add(placeReviewRepository.save(placeReview));
        }
        diary.setPlaceReviews(placeReviews);
        return getDiary(diary.getId());
    }

    @Transactional
    public void deleteCourse(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    @Transactional
    public DiaryDetailDto modifyDiary(Long diaryId, DiaryModifyRequestDto diaryModifyRequestDto) {

        ModifyDiaryDto diaryDto = diaryModifyRequestDto.getDiary();
        //순서로 정렬
        List<ModifyPlaceReviewDto> placeReviewDtos = diaryModifyRequestDto.getPlaceReviews()
                .stream()
                .sorted(Comparator.comparing(ModifyPlaceReviewDto::getPlaceOrder))
                .collect(Collectors.toList());

        //다이어리 변경
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        diary.modifyDiary(diaryDto);
        diaryRepository.save(diary);

        //순서로 정렬
        List<PlaceReview> placeReviews = placeReviewRepository.findAllByDiaryId(diaryId);
        placeReviews.stream().sorted(Comparator.comparing(PlaceReview::getPlaceOrder));

        for (int idx = 0; idx < placeReviews.stream().count(); idx++){
            PlaceReview placeReview = placeReviews.get(idx);
            //요청받은 placeOrder에 맞춰서 특정부분만 변경한다.
            for (ModifyPlaceReviewDto placeReviewDto: placeReviewDtos){
                if (placeReviewDto.getPlaceOrder() == placeReview.getPlaceOrder()){
                    placeReview.modifyPlaceReview(placeReviewDto);
                    placeReviewRepository.save(placeReview);
                }
            }
        }
        return getDiary(diaryId);

    }

}
