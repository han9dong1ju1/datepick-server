package app.hdj.datepick.domain.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserMetaDto {
    private Long id;
    private String uid;
    private String nickname;
    private String profileUrl;

    @QueryProjection
    public UserMetaDto(Long id, String uid, String nickname, String profileUrl) {
        this.id = id;
        this.uid = uid;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }
}
