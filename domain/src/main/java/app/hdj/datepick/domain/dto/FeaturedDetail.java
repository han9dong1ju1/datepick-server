package app.hdj.datepick.domain.dto;

import app.hdj.datepick.domain.entity.table.Featured;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedDetail {

    private Featured featured;
    private List<CourseInfo> courseInfos;

}
