package app.hdj.datepick.domain.pick.entity;

import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.global.common.entity.BaseEntity;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "place_pick")
public class PlacePick extends BaseTimeEntity<Long> {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",columnDefinition = "bigint", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", columnDefinition = "bigint", nullable = false)
    private Place place;

}
