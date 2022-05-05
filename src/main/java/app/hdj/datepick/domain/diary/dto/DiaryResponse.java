package app.hdj.datepick.domain.diary.dto;

import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.user.dto.UserPublic;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DiaryResponse {

    private Long id;
    private String content;
    private Float rating;
    private Long courseId;
    private UserPublic user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getContent(),
                diary.getRating(),
                diary.getCoursePlace().getCourse().getId(),
                UserPublic.from(diary.getCoursePlace().getCourse().getUser()),
                diary.getCreatedAt(),
                diary.getUpdatedAt()
        );
    }
}
