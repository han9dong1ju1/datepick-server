package app.hdj.datepick.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedMeta {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
}
