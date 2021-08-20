package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.PlaceEntity;
import app.hdj.datepick.data.entity.UserEntity;
import app.hdj.datepick.data.query.JpaFeaturedRepository;
import app.hdj.datepick.data.query.JpaPlaceRepository;
import app.hdj.datepick.domain.model.Place;
import app.hdj.datepick.domain.model.User;
import app.hdj.datepick.domain.repository.PlaceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;


@Repository
public class PlaceRepositoryImp implements PlaceRepository {

    private final ModelMapper mapper = new ModelMapper();
    private final JpaPlaceRepository jpaPlaceRepository;


    @Autowired
    public PlaceRepositoryImp(JpaPlaceRepository jpaPlaceRepository){ this.jpaPlaceRepository = jpaPlaceRepository; }


    @Override
    public List<Place> findPlacesWhereInCourse(Long courseId) {
        List<PlaceEntity> placeEntities = jpaPlaceRepository.findPlacesWhereInCourse(courseId);
        List<Place> places = mapper.map(placeEntities, new TypeToken<List<Place>>(){}.getType());
        return places;
    }

    @Override
    public List<Place> findPickedPlaces(Long userId) {
        List<PlaceEntity> placeEntities = jpaPlaceRepository.findPickedPlaces(userId);
        List<Place> places = mapper.map(placeEntities, new TypeToken<List<Place>>(){}.getType());
        return places;
    }
}
