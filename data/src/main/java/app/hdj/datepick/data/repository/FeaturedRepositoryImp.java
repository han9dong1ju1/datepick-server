package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.query.JpaFeaturedRepository;
import app.hdj.datepick.domain.entity.Course;
import app.hdj.datepick.domain.entity.Featured;
import app.hdj.datepick.domain.model.FeaturedDetail;
import app.hdj.datepick.domain.model.FeaturedMeta;
import app.hdj.datepick.domain.repository.FeaturedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Repository
public class FeaturedRepositoryImp implements FeaturedRepository {

    private final JpaFeaturedRepository jpaFeaturedRepository;

    @Override
    public List<FeaturedMeta> findAll() {
        List<Featured> featuredEntities = jpaFeaturedRepository.findAll();
        return featuredMetas;
    }


    @Override
    public FeaturedDetail findByIdWithDetail(Long id) {
        //Find Course By Featured
        List<Course> courseEntityList = jpaFeaturedRepository.findCourseListByFeaturedId(id);

        //Find Featured By Id
        Featured featuredEntity = jpaFeaturedRepository.findById(id).orElseThrow(()-> new NoSuchElementException(String.format("해당 id : %d 의 피쳐드가 존재하지 않습니다", id)));
        FeaturedDetail featuredDetail = new FeaturedDetail();

        //Make Featured Detail
        featuredDetail.setMeta(featuredMeta);
        featuredDetail.setContent(featuredEntity.getContent());
        featuredDetail.setCourses(courseList);

        return featuredDetail;
    }
}
