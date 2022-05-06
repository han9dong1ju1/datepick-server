package app.hdj.datepick.domain.diary.entity;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
