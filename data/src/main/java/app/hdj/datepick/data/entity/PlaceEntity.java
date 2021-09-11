package app.hdj.datepick.data.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "place")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //TODO kakao id 타입 결정
    @Column(name = "kakao_id", columnDefinition = "bigint not null")
    private String kakaoId;

    @Column(name = "name", columnDefinition = "varchar(200) not null")
    private String name;

    @Column(name = "rating", columnDefinition = "float not null default 0")
    private float rating;

    @Column(name = "address", columnDefinition = "varchar(200) not null")
    private String address;

    @Column(name = "latitude", columnDefinition = "double not null")
    private double latitude;

    @Column(name = "longitude", columnDefinition = "double not null")
    private double longitude;

    @Column(name = "contact", columnDefinition = "varchar(30) not null")
    private String contact;

    @Column(name = "type", columnDefinition = "varchar(30) not null")
    private String type;

    @Column(name = "subtype", columnDefinition = "varchar(30) not null")
    private String subtype;

    @Column(name = "category", columnDefinition = "varchar(50) not null")
    private String category;

    @Column(name = "homepage", columnDefinition = "varchar(200) not null")
    private String homepage;

    @Column(name = "review_count", columnDefinition = "int not null default 0")
    private int reviewCount;

    @Column(name = "blog_review_count", columnDefinition = "int not null default 0")
    private int blogReviewCount;

    @Column(name = "region", columnDefinition = "tinyint(4) not null")
    private int region;

    @Column(name = "pick_count", columnDefinition = "int not null default 0")
    private int pickCount;

    @Column(name = "updated_at", columnDefinition = "timestamp not null default now()")
    private Timestamp updatedAt;

}
