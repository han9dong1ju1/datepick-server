package app.hdj.datepick.domain.diary.dto;

import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.user.dto.UserPublic;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryResponse {

    private Long id;
    private String content;
    private Float rating;
    private Long courseId;
    private UserPublic user;

    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getContent(),
                diary.getRating(),
                diary.getCoursePlaceRelation().getCourse().getId(),
                UserPublic.from(diary.getCoursePlaceRelation().getCourse().getUser())

        );
    }
}
