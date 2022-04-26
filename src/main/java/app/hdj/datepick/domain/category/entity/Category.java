package app.hdj.datepick.domain.category.entity;

import app.hdj.datepick.domain.relation.entity.PlaceCategoryRelation;
import app.hdj.datepick.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category extends BaseEntity<Long> {

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<PlaceCategoryRelation> categoryPlaces;

}
