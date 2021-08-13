package app.hdj.datepick.data.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(50) not null")
    private String title;

    @Column(name = "user_id", columnDefinition = "int unsigned not null")
    private Long userId;

    @Column(name = "region", columnDefinition = "tinyint(4) not null")
    private Integer region;

    @Column(name = "expected_at", columnDefinition = "timestamp not null default now()")
    private Timestamp expectedAt;

    @Column(name = "pick_count", columnDefinition = "int unsigned not null default 0")
    private Long pickCount;

    @Column(name = "import_count", columnDefinition = "int unsigned not null default 0")
    private Long importCount;

    @Column(name = "is_private", columnDefinition = "tinyint(1) not null default 0")
    private Boolean isPrivate;

    @Column(name = "created_at", columnDefinition = "timestamp not null default now()")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp not null default now()")
    private Timestamp updatedAt;
    
}
