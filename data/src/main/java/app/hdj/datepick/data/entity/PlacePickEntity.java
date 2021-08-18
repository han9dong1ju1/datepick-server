package app.hdj.datepick.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "place_pick")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlacePickEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", columnDefinition = "bigint not null")
    private Long userId;

    @Column(name = "place_id", columnDefinition = "bigint not null")
    private Long placeId;

    @Column(name = "created_at", columnDefinition = "timestamp not null default now()")
    private Timestamp createdAt;
}
