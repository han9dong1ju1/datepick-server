package app.hdj.datepick.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String uid;
    private Boolean gender;
    private String nickname;
    private String profileUrl;

    public User(Long id, String uid, Boolean gender, String nickname, String profileUrl) {
        this.id = id;
        this.uid = uid;
        this.gender = gender;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

}
