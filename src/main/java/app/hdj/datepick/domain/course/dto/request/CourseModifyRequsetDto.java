package app.hdj.datepick.domain.course.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseModifyRequsetDto {

    private Long id;
    private String title;
    private String region;
    private LocalDateTime expectedAt;
    private String thumbNailUrl;

    private List<Long> placeRelations;


}
