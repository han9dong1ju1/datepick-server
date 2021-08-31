package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.entity.Featured;

import java.util.List;

public interface FeaturedService {
    List<FeaturedMeta> getAllFeaturedMetas();

    Featured getFeaturedDetail(Long id);
}
