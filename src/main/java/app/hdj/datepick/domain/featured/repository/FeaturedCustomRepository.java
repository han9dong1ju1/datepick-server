package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedDetail;
import app.hdj.datepick.domain.featured.dto.FeaturedMeta;

import java.util.List;

public interface FeaturedCustomRepository {

    FeaturedDetail findDetailBy(Long id);

}
