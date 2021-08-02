package app.hdj.datepick.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String uid;
    private String nickname;
    private String profileUrl;

    public User(Long id, String uid, String nickname, String profileUrl) {
        this.id = id;
        this.uid = uid;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

}
