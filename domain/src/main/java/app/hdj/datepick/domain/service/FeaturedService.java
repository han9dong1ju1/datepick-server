package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.FeaturedDetail;
import app.hdj.datepick.domain.model.FeaturedMeta;

import java.util.List;

public interface FeaturedService {
    List<FeaturedMeta> getAllFeaturedMetas();

    FeaturedDetail getFeaturedDetail(Long id);
}
