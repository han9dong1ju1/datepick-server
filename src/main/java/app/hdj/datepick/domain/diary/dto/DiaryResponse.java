package app.hdj.datepick.domain.diary.dto;

import app.hdj.datepick.domain.diary.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryResponse {

    private Long id;
    private String content;
    private Float rating;

    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getContent(),
                diary.getRating()
        );
    }
}
