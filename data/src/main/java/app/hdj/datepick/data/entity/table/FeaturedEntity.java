package app.hdj.datepick.data.entity.table;

import app.hdj.datepick.data.entity.BaseTimeEntity;
import app.hdj.datepick.data.entity.relation.CourseFeaturedRelationEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "featured")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeaturedEntity extends BaseTimeEntity<Long> {

    @Column(name = "title", columnDefinition = "varchar(128)", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @OneToMany(mappedBy = "featured", cascade = CascadeType.ALL)   // TODO: CascadeType 수정
    List<CourseFeaturedRelationEntity> courseFeaturedRelations;

}
