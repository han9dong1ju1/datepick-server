package app.hdj.datepick.domain.diary.entity;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Float rating;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @OneToOne(mappedBy = "diary", fetch = FetchType.LAZY)
    private CoursePlaceRelation coursePlace;

    @Builder
    private Diary(Long id, Float rating, String content, CoursePlaceRelation coursePlace) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.coursePlace = coursePlace;
    }
}
