package app.hdj.datepick.domain.tag.dto;

import app.hdj.datepick.domain.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagPublic {

    private Byte id;
    private String name;

    public static TagPublic from(Tag tag) {
        return new TagPublic(
                tag.getId(),
                tag.getName()
        );
    }

}
