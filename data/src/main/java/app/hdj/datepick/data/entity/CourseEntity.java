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
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(50) not null")
    private String title;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "region", nullable = false)
    private Byte region;

    @Column(name = "expected_at", nullable = false)
    private Timestamp expectedAt;

    @Column(name = "pick_count", nullable = false)
    @ColumnDefault("0")
    private Integer pickCount;

    @Column(name = "import_count", nullable = false)
    @ColumnDefault("0")
    private Integer importCount;

    @Column(name = "is_private", nullable = false)
    @ColumnDefault("false")
    private Boolean isPrivate;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Timestamp updatedAt;
    
}
