package app.hdj.datepick.domain.relation.entity;

import app.hdj.datepick.domain.category.entity.Category;
import app.hdj.datepick.domain.place.entity.Place;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PlaceCategoryRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Builder
    private PlaceCategoryRelation(Long id, Place place, Category category) {
        this.id = id;
        this.place = place;
        this.category = category;
    }
}
