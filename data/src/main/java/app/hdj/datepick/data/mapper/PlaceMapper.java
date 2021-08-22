package app.hdj.datepick.data.mapper;

import app.hdj.datepick.data.entity.PlaceEntity;
import app.hdj.datepick.domain.model.Place;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceMapper {

    private ModelMapper placeMapper = new ModelMapper();

    public Place EntityToModel(PlaceEntity entity){
        /**
         * exclude
         * picked, photos
         */
        Place model = placeMapper.map(entity, Place.class);
        model.mapCategory(entity.getType(), entity.getSubtype());
        return model;

    }

    public List<Place> EntitiesToModels(List<PlaceEntity> entities){
        List<Place> models = entities.stream().map(entity -> EntityToModel(entity)).collect(Collectors.toList());
        return models;
    }

    public PlaceEntity ModelToEntity(Place model, PlaceEntity oldEntity){
        /**
         * Model 에서 기존 OldEntity 내용을 참고해 Update Entity로 Mapping 한다.
         * 기존 OldEntity가 없을경우 null을 입력한다.
         */
        PlaceEntity entity = placeMapper.map(model, PlaceEntity.class);

        entity.setType(model.getCategory().getType());
        entity.setSubtype(model.getCategory().getSubtype());
        if (oldEntity != null) {
            entity = FillEntity(entity, oldEntity);
        }
        return entity;
    }


    public List<PlaceEntity> ModelsToEntities(List<Place> models, List<PlaceEntity> oldEntities){
        /**
         * Place Model List 에서 Place Entity List 로 변환
         * return : Id로 정렬되고 Update된 Entity List를 반환한다.
         */
        //id 기준 sort
        models.stream().sorted(Comparator.comparing(Place::getId));
        oldEntities.stream().sorted(Comparator.comparing(PlaceEntity::getId));

        //TODO 예외처리
        assert(models.size() == oldEntities.size());

        PlaceEntity oldEntity;
        Place model;
        List<PlaceEntity> entities = new ArrayList<PlaceEntity>();
        int size = models.size();
        for (int i = 0; i < size; i++){
            oldEntity = oldEntities.get(i);
            model = models.get(i);
            entities.add(ModelToEntity(model, oldEntity));
        }

        return entities;
    }


    public PlaceEntity FillEntity(PlaceEntity newEntity, PlaceEntity oldEntity){
        newEntity.setContact(oldEntity.getContact());
        newEntity.setHomepage(oldEntity.getHomepage());
        newEntity.setReviewCount(oldEntity.getReviewCount());
        newEntity.setBlogReviewCount(oldEntity.getBlogReviewCount());
        newEntity.setRegion(oldEntity.getRegion());
        newEntity.setPickCount(oldEntity.getPickCount());
        newEntity.setUpdatedAt(oldEntity.getUpdatedAt());
        return newEntity;
    }
}
