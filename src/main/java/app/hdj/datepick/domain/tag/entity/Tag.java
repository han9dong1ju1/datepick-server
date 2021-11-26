package app.hdj.datepick.domain.tag.entity;

import app.hdj.datepick.global.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tag extends BaseEntity<Long> {

    @Column(nullable = false)
    String name;

}
