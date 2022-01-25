package app.hdj.datepick.global.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public class BaseEntity<ID extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private ID id;
}
