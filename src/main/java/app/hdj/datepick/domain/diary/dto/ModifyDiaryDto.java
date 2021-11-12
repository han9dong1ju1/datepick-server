package app.hdj.datepick.domain.diary.dto;

import app.hdj.datepick.global.common.enums.Style;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;


@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModifyDiaryDto {
    private Long courseId;
    private String title;
    private String style;
}
