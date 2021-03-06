package app.hdj.datepick.domain.tag.dto;

import app.hdj.datepick.domain.tag.entity.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TagWithCountResponse {

    private Integer id;
    private String name;
    private Long courseCount;

    public static TagWithCountResponse from(Tag tag) {
        return new TagWithCountResponse(tag.getId(),
                                        tag.getName(),
                                        (long) tag.getTagCourses().size());
    }
}
