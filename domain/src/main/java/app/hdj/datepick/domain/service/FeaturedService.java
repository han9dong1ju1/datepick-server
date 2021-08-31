package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.entity.Featured;
import app.hdj.datepick.domain.entity.FeaturedMeta;

import java.util.List;

public interface FeaturedService {
    List<FeaturedMeta> getAllFeaturedMetas();

    Featured getFeatured(Long id);
}
