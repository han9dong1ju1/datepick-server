package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.model.FeaturedDetail;
import app.hdj.datepick.domain.model.FeaturedMeta;

import java.util.List;

public interface FeaturedRepository {

    List<FeaturedMeta> findAll();

    FeaturedDetail findByIdWithDetail(Long id);
}
