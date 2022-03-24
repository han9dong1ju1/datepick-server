package app.hdj.datepick.domain.relation.dto;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoursePlacePublic {

    private Long id;
    private Long placeId;

    public static CoursePlacePublic from(CoursePlaceRelation coursePlaceRelation) {
        return new CoursePlacePublic(
                coursePlaceRelation.getId(),
                coursePlaceRelation.getPlace().getId()
        );
    }
}
