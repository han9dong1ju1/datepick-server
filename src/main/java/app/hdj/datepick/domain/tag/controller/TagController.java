package app.hdj.datepick.domain.tag.controller;

import app.hdj.datepick.domain.tag.entity.Tag;
import app.hdj.datepick.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping("")
    public List<Tag> getTagList() {
        return tagService.getTagList();
    }

}
