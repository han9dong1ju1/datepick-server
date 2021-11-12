package app.hdj.datepick.domain.featured.entity;


import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import app.hdj.datepick.domain.relation.entity.CourseFeaturedRelation;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "featured")
public class Featured extends BaseTimeEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(name = "is_pinned", nullable = false)
    @ColumnDefault("false")
    private Boolean isPinned;


    @OneToMany(mappedBy = "featured", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CourseFeaturedRelation> courseFeaturedRelations;

}
