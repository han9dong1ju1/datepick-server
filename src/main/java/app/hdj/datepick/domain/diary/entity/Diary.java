package app.hdj.datepick.domain.diary.entity;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.diary.dto.ModifyDiaryDto;
import app.hdj.datepick.domain.review.entity.PlaceReview;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseAllTimeEntity;
import app.hdj.datepick.global.common.enums.Style;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseAllTimeEntity<Long> {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long likeCount;

    @Column
    @Enumerated(EnumType.STRING)
    private Style style;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PlaceReview> placeReviews;

    public void modifyDiary(ModifyDiaryDto modifyDiaryDto){
        this.style = Style.findByString(modifyDiaryDto.getStyle());
        this.title = modifyDiaryDto.getTitle();
    }

}
