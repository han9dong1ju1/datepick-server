package app.hdj.datepick.domain.course.dto;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ModifyCourseDto {
    private Long id;
    private String title;
    private String region;
    private LocalDateTime expectedAt;
    private String thumbNailUrl;
}
