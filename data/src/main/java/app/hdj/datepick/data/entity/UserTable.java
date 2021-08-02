package app.hdj.datepick.data.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTable {
    @Id
    private Long id;

    @Column(nullable = false, length = 128)
    private String uid;

    @Column(nullable = false ,unique = true, length = 16)
    private String nickname;

    @Column(nullable = false, length = 255, name = "photo_url")
    private String profileUrl;

    @CreatedDate
    private Timestamp created_at;
}
