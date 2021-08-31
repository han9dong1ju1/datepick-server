package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.entity.Featured;
import app.hdj.datepick.domain.entity.FeaturedMeta;

import java.util.List;
import java.util.Optional;

public interface FeaturedRepository {

    List<FeaturedMeta> findAll();

    Optional<Featured> findById(Long id);
}
