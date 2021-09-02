package app.hdj.datepick.global.common.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public class BaseEntity<ID extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private ID id;
}
