package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.dto.FeaturedDetail;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.featured.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedService {

    private final FeaturedRepository featuredRepository;

    public List<Featured> getAllFeaturedMetas() {
        return featuredRepository.findAll();
    }

    public FeaturedDetail getFeatured(Long id) {
        FeaturedDetail featuredDetail = featuredRepository.findDetailBy(id);
        return featuredDetail;
    }
}
