package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.query.JpaFeaturedRepository;
import app.hdj.datepick.domain.entity.Featured;
import app.hdj.datepick.domain.entity.FeaturedMeta;
import app.hdj.datepick.domain.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FeaturedRepositoryImp implements FeaturedRepository {

    private final JpaFeaturedRepository jpaFeaturedRepository;

    @Override
    public List<FeaturedMeta> findAll() {
//        return jpaFeaturedRepository.findAll().stream()
//                .map(featured -> (FeaturedMeta)featured)
//                .collect(Collectors.toList());
        return jpaFeaturedRepository.findAllBy();
    }


    @Override
    public Optional<Featured> findById(Long id) {
        return jpaFeaturedRepository.findById(id);
    }
}
