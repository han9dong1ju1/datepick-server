package app.hdj.datepick.data.query;

import app.hdj.datepick.domain.entity.FeaturedMeta;
import app.hdj.datepick.domain.entity.Featured;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFeaturedRepository extends JpaRepository<Featured, Long> {
    List<FeaturedMeta> findAllBy();
}
