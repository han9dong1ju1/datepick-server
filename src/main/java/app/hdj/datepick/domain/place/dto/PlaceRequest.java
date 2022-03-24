package app.hdj.datepick.domain.place.dto;

import app.hdj.datepick.domain.place.entity.Place;
import com.google.common.collect.Sets;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
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
