package app.hdj.datepick.domain.diary.entity;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseTimeEntity<Long> {

    @Column(nullable = false)
    private Float rating;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @OneToOne(mappedBy = "diary", fetch = FetchType.LAZY)
    private CoursePlaceRelation coursePlaceRelation;

}
