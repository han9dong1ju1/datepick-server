package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.CourseEntity;
import app.hdj.datepick.data.entity.FeaturedEntity;
import app.hdj.datepick.data.query.JpaFeaturedRepository;
import app.hdj.datepick.domain.model.Course;
import app.hdj.datepick.domain.model.Featured;
import app.hdj.datepick.domain.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Repository
public class FeaturedRepositoryImp implements FeaturedRepository {

    private final ModelMapper mapper;
    private final JpaFeaturedRepository jpaFeaturedRepository;

    @Override
    public List<Featured> findAll() {
        List<FeaturedEntity> featuredEntities = jpaFeaturedRepository.findAll();
        Type listType = new TypeToken<List<Featured>>(){}.getType();
        return mapper.map(featuredEntities, listType);
    }

    @Override
    public Featured findById(Long id) {
        //Find Course By Featured
        List<CourseEntity> courseEntityList = jpaFeaturedRepository.findCourseListByFeaturedId(id);
        Type listType = new TypeToken<List<Course>>(){}.getType();
        List<Course> courseList = mapper.map(courseEntityList, listType);

        //Find Featured By Id
        FeaturedEntity featuredEntity = jpaFeaturedRepository.findById(id).orElseThrow(()-> new NoSuchElementException(String.format("해당 id : %d 의 피쳐드가 존재하지 않습니다", id)));
        Featured featured = mapper.map(featuredEntity, Featured.class);

        //Make Featured Detail
        featured.setCourses(courseList);

        return featured;
    }
}
