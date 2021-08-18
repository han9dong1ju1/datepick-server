package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.PlaceEntity;
import app.hdj.datepick.data.entity.PlacePickEntity;
import app.hdj.datepick.data.query.JpaFeaturedRepository;
import app.hdj.datepick.data.query.JpaPlacePickRepository;
import app.hdj.datepick.domain.model.Course;
import app.hdj.datepick.domain.model.FeaturedMeta;
import app.hdj.datepick.domain.model.Place;
import app.hdj.datepick.domain.repository.PlacePickRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;


@Repository
public class PlacePickRepositoryImp implements PlacePickRepository {

    private ModelMapper mapper = new ModelMapper();
    private final JpaPlacePickRepository jpaPlacePickRepository;

    @Autowired
    public PlacePickRepositoryImp(JpaPlacePickRepository jpaPlacePickRepository) {
        this.jpaPlacePickRepository = jpaPlacePickRepository;
    }

    @Override
    public List<Place> findPickedPlaces(Long userId) {
        List<PlaceEntity> placeEntities =  jpaPlacePickRepository.findPickedPlacesByUserId(userId);
        List<Place> places = mapper.map(placeEntities, new TypeToken<List<Place>>(){}.getType());
        System.out.println(places.toString());
        return places;
    }
}
