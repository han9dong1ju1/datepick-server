package app.hdj.datepick.domain.course.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CourseRequest {

    @NotEmpty
    private String title;

    private LocalDateTime meetAt;

    @NotNull
    private Boolean isPrivate;

    private List<@Positive Byte> tagIds;

}
