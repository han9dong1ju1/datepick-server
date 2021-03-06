package app.hdj.datepick.domain.tag.controller;

import app.hdj.datepick.domain.tag.dto.TagWithCountResponse;
import app.hdj.datepick.domain.tag.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping("")
    public List<TagWithCountResponse> getTagList() {
        return tagService.getTagList();
    }
}
