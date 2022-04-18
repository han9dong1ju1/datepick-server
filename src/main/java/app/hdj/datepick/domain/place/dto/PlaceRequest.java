package app.hdj.datepick.domain.place.dto;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@NoArgsConstructor
public class PlaceRequest {

    @NotNull
    private String kakaoId;
    @NotNull
    private String name;
    @NotNull
    private String categories;
    @NotNull
    private String address;

    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    public static Set<String> parseCategories(String categories) {
        return Sets.newHashSet(categories.trim().split(">"));
    }

}
