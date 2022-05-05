package app.hdj.datepick.domain.category.entity;

import app.hdj.datepick.domain.relation.entity.PlaceCategoryRelation;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    private String name;

    @Builder
    private Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<PlaceCategoryRelation> categoryPlaces = List.of();
}
