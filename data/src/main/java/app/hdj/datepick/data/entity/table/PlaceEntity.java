package app.hdj.datepick.data.entity.table;

import app.hdj.datepick.data.entity.BaseEntity;
import app.hdj.datepick.data.entity.relation.CoursePlaceRelationEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@Entity(name = "place")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceEntity extends BaseEntity<Long> {

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)   // TODO: CascadeType 수정
    List<CoursePlaceRelationEntity> coursePlaceRelations;

}
