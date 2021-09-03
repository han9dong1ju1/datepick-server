package app.hdj.datepick.domain.featured.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FeaturedMeta {

    private Long id;
    private String title;
    private String description;
    private String photoUrl;
}
