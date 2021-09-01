package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.dto.FeaturedDetail;
import app.hdj.datepick.domain.entity.table.Featured;

import java.util.List;

public interface FeaturedService {
    List<Featured> getAllFeaturedMetas();

    FeaturedDetail getFeatured(Long id);
}
