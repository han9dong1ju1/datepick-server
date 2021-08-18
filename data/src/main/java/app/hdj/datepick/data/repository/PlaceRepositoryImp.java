package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.query.JpaFeaturedRepository;
import app.hdj.datepick.data.query.JpaPlaceRepository;
import app.hdj.datepick.domain.repository.PlaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PlaceRepositoryImp implements PlaceRepository {

    private final ModelMapper mapper = new ModelMapper();
    private final JpaPlaceRepository jpaPlaceRepository;

    @Autowired
    public PlaceRepositoryImp(JpaPlaceRepository jpaPlaceRepository){ this.jpaPlaceRepository = jpaPlaceRepository; }


}
