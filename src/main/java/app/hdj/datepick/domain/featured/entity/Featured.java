package app.hdj.datepick.domain.featured.entity;

import app.hdj.datepick.global.common.entity.BaseAllTimeEntity;
import app.hdj.datepick.global.relation.entity.CourseFeaturedRelation;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Featured extends BaseAllTimeEntity<Long> {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column(nullable = false)
    private String photoUrl;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isPinned;

    @OneToMany(mappedBy = "featured", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CourseFeaturedRelation> courseFeaturedRelations;

}
