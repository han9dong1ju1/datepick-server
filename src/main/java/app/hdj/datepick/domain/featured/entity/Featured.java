package app.hdj.datepick.domain.featured.entity;

import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Featured extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isPinned;

    @Builder
    private Featured(Long id, String title, String subtitle, String content, String imageUrl, Boolean isPinned) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.imageUrl = imageUrl;
        this.isPinned = Optional.ofNullable(isPinned).orElse(false);
    }
}
