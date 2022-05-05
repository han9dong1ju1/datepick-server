package app.hdj.datepick.domain.tag.service;

import app.hdj.datepick.domain.tag.dto.TagWithCountResponse;
import app.hdj.datepick.domain.tag.repository.TagRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public List<TagWithCountResponse> getTagList() {
        return tagRepository.findAll()
            .stream()
            .map(TagWithCountResponse::from)
            .collect(Collectors.toList());
    }
}
