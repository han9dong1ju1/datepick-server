package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedDetail;

public interface FeaturedCustomRepository {

    FeaturedDetail findDetailById(Long id);

}
