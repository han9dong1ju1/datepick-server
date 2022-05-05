package app.hdj.datepick.domain.tag.dto;

import app.hdj.datepick.domain.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagResponse {

    private Integer id;
    private String name;
    private Long courseCount;

    public static TagResponse from(Tag tag) {
        return new TagResponse(
                tag.getId(),
                tag.getName(),
                (long) tag.getTagCourses().size()
        );
    }

}
