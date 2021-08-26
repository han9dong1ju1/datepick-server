package app.hdj.datepick.dto.featured;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedMetaResponseDto {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
}
