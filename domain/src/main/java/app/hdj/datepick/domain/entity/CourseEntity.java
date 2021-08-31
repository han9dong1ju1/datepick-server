package app.hdj.datepick.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(50) not null")
    private String title;

    @Column(name = "user_id", columnDefinition = "bigint not null")
    private Long userId;

    @Column(name = "region", columnDefinition = "tinyint(4) not null")
    private Integer region;

    @Column(name = "expected_at", columnDefinition = "timestamp not null default now()")
    private Timestamp expectedAt;

    @Column(name = "pick_count", columnDefinition = "int not null default 0")
    private Integer pickCount;

    @Column(name = "import_count", columnDefinition = "int not null default 0")
    private Integer importCount;

    @Column(name = "is_private", columnDefinition = "boolean not null default false")
    private Boolean isPrivate;

    @Column(name = "created_at", columnDefinition = "timestamp not null default now()")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp not null default now()")
    private Timestamp updatedAt;
    
}
