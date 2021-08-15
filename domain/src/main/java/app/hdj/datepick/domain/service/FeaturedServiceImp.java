package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.FeaturedDetail;
import app.hdj.datepick.domain.model.FeaturedMeta;
import app.hdj.datepick.domain.repository.FeaturedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeaturedServiceImp implements FeaturedService {

    private final FeaturedRepository featuredRepository;

    @Autowired
    public FeaturedServiceImp(FeaturedRepository featuredRepository) {
        this.featuredRepository = featuredRepository;
    }

    @Override
    public List<FeaturedMeta> getAllFeaturedMetas() {
        return featuredRepository.findAll();
    }

    @Override
    public FeaturedDetail getFeaturedDetail(Long id) {
        return featuredRepository.findByIdWithDetail(id);
    }
}
