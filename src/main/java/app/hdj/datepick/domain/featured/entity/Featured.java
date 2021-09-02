package app.hdj.datepick.domain.featured.entity;


import app.hdj.datepick.global.common.entity.BaseEntity;
import app.hdj.datepick.global.common.entity.CourseFeaturedRelation;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "featured")
public class Featured extends BaseEntity<Long> {


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

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

}
