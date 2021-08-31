package app.hdj.datepick.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "featured")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Featured {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(128) not null default ''")
    private String title;

    @Column(name = "description", columnDefinition = "varchar(255) not null default ''")
    private String description;

    @Column(name = "content", columnDefinition = "text not null default ''")
    private String content;

    @Column(name = "photo_url", columnDefinition = "varchar(255) not null default ''")
    private String photoUrl;

    @Column(name = "created_at", columnDefinition = "timestamp not null default now()")
    private Timestamp createdAt;
}
