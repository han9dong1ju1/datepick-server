package app.hdj.datepick.domain.course.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@ToString
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModifyCourseDto {
    private Long id;
    private String title;
    private String region;
    private LocalDateTime expectedAt;
    private String thumbNailUrl;
}
