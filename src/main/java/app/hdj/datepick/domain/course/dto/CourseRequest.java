package app.hdj.datepick.domain.course.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseRequest {

    @NotEmpty
    private String title;
    private LocalDateTime meetAt;
    @NotNull
    private Boolean isPrivate;
    private List<@Positive Integer> tagIds;
}
