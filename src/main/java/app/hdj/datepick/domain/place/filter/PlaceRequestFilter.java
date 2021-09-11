package app.hdj.datepick.domain.place.filter;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

//TODO filter 종류 선정

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PlaceRequestFilter {

    private String sort;
    private String idType;
    private Long id;
    private String filter;
}