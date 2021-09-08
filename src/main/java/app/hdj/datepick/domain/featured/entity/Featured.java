package app.hdj.datepick.domain.featured.entity;


import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import app.hdj.datepick.global.common.entity.relation.CourseFeaturedRelation;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "featured")
public class Featured extends BaseTimeEntity<Long> {

    @Column(name = "title", columnDefinition = "varchar(128)", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @OneToMany(mappedBy = "featured", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CourseFeaturedRelation> courseFeaturedRelations;

}
