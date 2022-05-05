package app.hdj.datepick.domain.relation.dto;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CoursePlaceResponse {

    private Long id;
    private Long placeId;

    public static CoursePlaceResponse from(CoursePlaceRelation coursePlaceRelation) {
        return new CoursePlaceResponse(coursePlaceRelation.getId(),
                                       coursePlaceRelation.getPlace().getId());
    }
}
