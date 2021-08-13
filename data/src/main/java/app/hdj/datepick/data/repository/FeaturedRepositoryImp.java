package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.CourseTable;
import app.hdj.datepick.data.entity.FeaturedTable;
import app.hdj.datepick.data.entity.UserTable;
import app.hdj.datepick.data.query.JpaFeaturedRepository;
import app.hdj.datepick.domain.dto.Course;
import app.hdj.datepick.domain.dto.FeaturedDetail;
import app.hdj.datepick.domain.dto.FeaturedMeta;
import app.hdj.datepick.domain.dto.User;
import app.hdj.datepick.domain.repository.FeaturedRepository;
import com.fasterxml.jackson.core.JsonParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Repository
public class FeaturedRepositoryImp implements FeaturedRepository {

    private final ModelMapper mapper = new ModelMapper();
    private final JpaFeaturedRepository jpaFeaturedRepository;

    @Autowired
    public FeaturedRepositoryImp(JpaFeaturedRepository jpaFeaturedRepository) {
        this.jpaFeaturedRepository = jpaFeaturedRepository;
    }

    @Override
    public List<FeaturedMeta> findAll() {
        List<FeaturedTable> featuredTables = jpaFeaturedRepository.findAll();
        Type listType = new TypeToken<List<FeaturedMeta>>(){}.getType();
        List<FeaturedMeta> featuredMetas = mapper.map(featuredTables, listType);
        return featuredMetas;
    }


    @Override
    public FeaturedDetail findByIdWithDetail(Long id) {
        //Find Course By Featured
        List<CourseTable> courseTableList = jpaFeaturedRepository.findCourseListByFeaturedId(id);
        Type listType = new TypeToken<List<Course>>(){}.getType();
        List<Course> courseList = mapper.map(courseTableList, listType);

        //Find Featured By Id
        FeaturedTable featuredTable = jpaFeaturedRepository.findById(id).orElseThrow(()-> new NoSuchElementException(String.format("해당 id : %d 의 피쳐드가 존재하지 않습니다", id)));
        FeaturedMeta featuredMeta = mapper.map(featuredTable, FeaturedMeta.class);
        FeaturedDetail featuredDetail = new FeaturedDetail();

        //Make Featured Detail
        featuredDetail.setMeta(featuredMeta);
        featuredDetail.setContent(featuredTable.getContent());
        featuredDetail.setCourses(courseList);

        return featuredDetail;
    }
}
