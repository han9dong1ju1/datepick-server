package app.hdj.datepick.domain.place.dto.request;

import app.hdj.datepick.domain.place.dto.PlaceCourseMetaDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
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
