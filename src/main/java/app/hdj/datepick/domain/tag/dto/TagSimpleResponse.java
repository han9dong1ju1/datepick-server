package app.hdj.datepick.domain.tag.dto;

import app.hdj.datepick.domain.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagSimpleResponse {

    private Integer id;
    private String name;

    public static TagSimpleResponse from(Tag tag) {
        return new TagSimpleResponse(
                tag.getId(),
                tag.getName()
        );
    }

}
