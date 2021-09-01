package app.hdj.datepick.domain.repository.support;


import app.hdj.datepick.domain.dto.FeaturedDetail;
import app.hdj.datepick.domain.entity.table.Featured;

import java.util.Optional;

public interface FeaturedSupportRepository {
    FeaturedDetail findDetailBy(Long id);
}
