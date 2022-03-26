package app.hdj.datepick.domain.tag.service;

import app.hdj.datepick.domain.tag.entity.Tag;
import app.hdj.datepick.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> getTagList() {
        return tagRepository.findAll();
    }

}
