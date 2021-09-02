package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.featured.dto.FeaturedDetail;
import app.hdj.datepick.domain.featured.entity.Featured;

import java.util.List;

public interface FeaturedService {
    List<Featured> getAllFeaturedMetas();

    FeaturedDetail getFeatured(Long id);
}
