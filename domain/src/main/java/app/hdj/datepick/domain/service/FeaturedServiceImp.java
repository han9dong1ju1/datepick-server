package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.entity.Featured;
import app.hdj.datepick.domain.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeaturedServiceImp implements FeaturedService {

    private final FeaturedRepository featuredRepository;

    @Override
    public List<FeaturedMeta> getAllFeaturedMetas() {
        return featuredRepository.findAll();
    }

    @Override
    public Featured getFeaturedDetail(Long id) {
        return featuredRepository.findByIdWithDetail(id);
    }
}
