package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.dto.FeaturedDetail;
import app.hdj.datepick.domain.entity.table.Featured;
import app.hdj.datepick.domain.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedServiceImp implements FeaturedService {

    private final FeaturedRepository featuredRepository;

    @Override
    public List<Featured> getAllFeaturedMetas() {
        return featuredRepository.findAll();
    }

    @Override
    public FeaturedDetail getFeatured(Long id) {
        FeaturedDetail featuredDetail = featuredRepository.findDetailBy(id);
        return featuredDetail;
    }
}
