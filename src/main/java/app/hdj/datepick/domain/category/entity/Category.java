package app.hdj.datepick.domain.category.entity;

import app.hdj.datepick.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category extends BaseEntity<Long> {

    @Column(unique = true)
    private String name;

    @Column
    @ColumnDefault("0")
    private Long placeCount;

}
