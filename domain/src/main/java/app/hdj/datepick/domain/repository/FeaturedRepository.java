package app.hdj.datepick.domain.repository;


import app.hdj.datepick.domain.model.FeaturedDetail;
import app.hdj.datepick.domain.model.FeaturedMeta;

import java.util.List;


public interface FeaturedRepository {

    public List<FeaturedMeta> findAll();

    public FeaturedDetail findByIdWithDetail(Long id);
}
