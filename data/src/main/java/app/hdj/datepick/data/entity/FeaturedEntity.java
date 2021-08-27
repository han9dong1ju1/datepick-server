package app.hdj.datepick.data.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "featured")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"})
public class FeaturedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(128)", nullable = false)
    @ColumnDefault("")
    private String title;

    @Column(name = "description", columnDefinition = "varchar(255)", nullable = false)
    @ColumnDefault("")
    private String description;

    @Column(name = "content", columnDefinition = "text", nullable = false)
    @ColumnDefault("")
    private String content;

    @Column(name = "photo_url", columnDefinition = "varchar(255)", nullable = false)
    @ColumnDefault("")
    private String photoUrl;

    @Column(name = "created_at", columnDefinition = "timestamp", nullable = false)
    @CreatedDate
    private Timestamp createdAt;

}
