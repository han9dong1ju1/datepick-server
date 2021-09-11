package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.PlaceEntity;
import app.hdj.datepick.data.entity.UserEntity;
import app.hdj.datepick.data.mapper.PlaceMapper;
import app.hdj.datepick.data.query.JpaPlaceRepository;
import app.hdj.datepick.domain.model.Place;
import app.hdj.datepick.domain.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;


@Repository
public class PlaceRepositoryImp implements PlaceRepository {

    private final PlaceMapper placeMapper;
    private final JpaPlaceRepository jpaPlaceRepository;


    @Autowired
    public PlaceRepositoryImp(PlaceMapper placeMapper, JpaPlaceRepository jpaPlaceRepository) {
        this.placeMapper = placeMapper;
        this.jpaPlaceRepository = jpaPlaceRepository;
    }

    @Override
    public List<Place> findPlacesWhereInCourse(Long courseId) {
        List<PlaceEntity> placeEntities = jpaPlaceRepository.findPlacesWhereInCourse(courseId);
        List<Place> places = placeMapper.EntitiesToModels(placeEntities);
        return places;
    }

    @Override
    public List<Place> findPickedPlaces(Long userId) {
        List<PlaceEntity> placeEntities = jpaPlaceRepository.findPickedPlaces(userId);
        List<Place> places = placeMapper.EntitiesToModels(placeEntities);
        return places;
    }

    @Override
    public void savePlace(Place place) {
        //TODO kakao id가 중복되면 예외알림
        place.setId(null);
        PlaceEntity placeEntity = placeMapper.ModelToEntity(place, null);
        System.out.println(placeEntity.toString());
        jpaPlaceRepository.save(placeEntity);
    }

}
