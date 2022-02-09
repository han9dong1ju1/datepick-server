package app.hdj.datepick.global.util;

import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PagingUtil {

    private final EntityManager em;

    private Querydsl getQuerydsl(Class classType) {
        PathBuilder builder = new PathBuilderFactory().create(classType);
        return new Querydsl(em, builder);
    }

    public <T> PageImpl<T> getPageImpl(Pageable pageable, JPQLQuery<T> query, Class classType) {
        long totalCount = query.fetchCount();
        List<T> results = getQuerydsl(classType).applyPagination(pageable, query).fetch();
        return new PageImpl<>(results, pageable, totalCount);
    }

}
