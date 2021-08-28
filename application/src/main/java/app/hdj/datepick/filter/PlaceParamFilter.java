package app.hdj.datepick.filter;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceParamFilter {
    //거리순, 리뷰개수순, 평점순,
    String sort;
    int limit;
    String filter;
    boolean pick;
    Long courseId;
    //TODO security 적용후 param 에서 삭제
    Long userId;
}
