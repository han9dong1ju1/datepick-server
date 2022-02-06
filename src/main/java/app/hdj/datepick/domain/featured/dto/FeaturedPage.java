package app.hdj.datepick.domain.featured.dto;

import app.hdj.datepick.domain.featured.entity.Featured;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeaturedPage {
    private long totalCount;
    private int currentPage;
    private Boolean last;
    List<Featured> content;
}
