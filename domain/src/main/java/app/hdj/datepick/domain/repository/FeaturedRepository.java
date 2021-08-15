package app.hdj.datepick.domain.repository;


import app.hdj.datepick.domain.model.FeaturedDetail;
import app.hdj.datepick.domain.model.FeaturedMeta;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeaturedRepository {

    public List<FeaturedMeta> findAll();

    public FeaturedDetail findByIdWithDetail(Long id);
}
