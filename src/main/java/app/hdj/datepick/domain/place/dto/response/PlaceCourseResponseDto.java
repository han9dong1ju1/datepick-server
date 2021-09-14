package app.hdj.datepick.domain.place.dto.response;

import app.hdj.datepick.domain.place.dto.PlaceCourseMetaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlaceCourseResponseDto {

    //필요한 정보
    private Long courseId;
    private List<PlaceCourseMetaDto> placeCourseMetaDto;

}
