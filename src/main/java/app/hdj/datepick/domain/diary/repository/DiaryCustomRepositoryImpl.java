package app.hdj.datepick.domain.diary.repository;


import app.hdj.datepick.domain.diary.dto.DiaryDetailDto;
import app.hdj.datepick.domain.diary.dto.DiaryMetaDto;
import app.hdj.datepick.domain.diary.dto.QDiaryDetailDto;
import app.hdj.datepick.domain.diary.dto.QDiaryMetaDto;
import app.hdj.datepick.domain.diary.dto.PlaceReviewDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.hdj.datepick.domain.diary.entity.QDiary.diary;

@Slf4j
@RequiredArgsConstructor
@Repository
public class DiaryCustomRepositoryImpl implements DiaryCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<DiaryMetaDto> findMyDiariesPage(Pageable pageable) {

        //TODO userId
        Long userId = 12L;

        JPAQuery<DiaryMetaDto> query = jpaQueryFactory
                .select(new QDiaryMetaDto(
                    diary.id,
                    diary.course.id,
                    diary.user.id,
                    diary.title,
                    diary.likeCount,
                    diary.style.stringValue()
                ))
                .from(diary)
                .where(diary.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        for (Sort.Order o : pageable.getSort()){
            PathBuilder pathBuilder = new PathBuilder(diary.getType(), diary.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }
        QueryResults<DiaryMetaDto> results = query.fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public DiaryDetailDto findDiaryDetail(Long diaryId, List<PlaceReviewDto> placeReviews) {
        DiaryDetailDto diaryDetailDto =  jpaQueryFactory
                .select(new QDiaryDetailDto(
                        diary.id,
                        diary.course.id,
                        diary.user.id,
                        diary.title,
                        diary.likeCount,
                        diary.style.stringValue(),
                        Expressions.constant(placeReviews)
                        ))
                .from(diary)
                .where(diary.id.eq(diaryId))
                .fetchOne();
        return diaryDetailDto;
    }
}
