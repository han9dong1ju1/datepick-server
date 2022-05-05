package app.hdj.datepick.domain.diary.repository;

import static app.hdj.datepick.domain.diary.entity.QDiary.diary;

import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class DiaryCustomRepositoryImpl implements DiaryCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public Page<Diary> findDiaryPage(
        DiaryFilterParam diaryFilterParam, PagingParam pagingParam, CustomSort sort
    ) {
        JPQLQuery<Diary> query = jpaQueryFactory.selectFrom(diary)
            .leftJoin(diary.coursePlace)
            .fetchJoin()
            .where(diary.coursePlace.course.isPrivate.eq(false));
        query = filterDiaries(query, diaryFilterParam);
        query = applySort(query, sort);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize());
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    @Override
    public Page<Diary> findMyDiaryPage(
        DiaryFilterParam diaryFilterParam, PagingParam pagingParam, CustomSort sort
    ) {
        JPQLQuery<Diary> query = jpaQueryFactory.selectFrom(diary)
            .leftJoin(diary.coursePlace)
            .fetchJoin();
        query = filterDiaries(query, diaryFilterParam);
        query = applySort(query, sort);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize());
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    private JPQLQuery<Diary> filterDiaries(
        JPQLQuery<Diary> query, DiaryFilterParam diaryFilterParam
    ) {
        if (diaryFilterParam.getKeyword() != null) {
            query = query.where(diary.content.contains(diaryFilterParam.getKeyword()));
        }

        if (diaryFilterParam.getUserId() != null) {
            query = query.where(diary.coursePlace.course.user.id.eq(diaryFilterParam.getUserId()));
        }

        if (diaryFilterParam.getPlaceId() != null) {
            query = query.where(diary.coursePlace.place.id.eq(diaryFilterParam.getPlaceId()));
        }

        if (diaryFilterParam.getCourseId() != null) {
            query = query.where(diary.coursePlace.course.id.eq(diaryFilterParam.getCourseId()));
        }

        return query;
    }

    private JPQLQuery<Diary> applySort(JPQLQuery<Diary> query, CustomSort sort) {
        if (sort == null) {
            sort = CustomSort.LATEST;
        }

        switch (sort) {
            case LATEST:
                query = query.orderBy(diary.createdAt.desc());
                break;
            case RATING_ASC:
                query = query.orderBy(diary.rating.asc());
                break;
            case RATING_DESC:
                query = query.orderBy(diary.rating.desc());
                break;
        }

        return query;
    }
}
