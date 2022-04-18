package app.hdj.datepick.domain.diary.repository;

import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import static app.hdj.datepick.domain.diary.entity.QDiary.diary;


@Slf4j
@RequiredArgsConstructor
@Repository
public class DiaryCustomRepositoryImpl implements DiaryCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public Page<Diary> findDiaryPage(DiaryFilterParam diaryFilterParam, PagingParam pagingParam, Sort sort) {
        JPQLQuery<Diary> query = jpaQueryFactory
                .selectFrom(diary)
                .where(diary.coursePlaceRelation.course.isPrivate.eq(false));

        query = filterDiaries(query, diaryFilterParam);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(), sort);
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    @Override
    public Page<Diary> findMyDiaryPage(DiaryFilterParam diaryFilterParam, PagingParam pagingParam, Sort sort) {
        JPQLQuery<Diary> query = jpaQueryFactory
                .selectFrom(diary);

        query = filterDiaries(query, diaryFilterParam);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(), sort);
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    private JPQLQuery<Diary> filterDiaries(JPQLQuery<Diary> query, DiaryFilterParam diaryFilterParam) {
        if (diaryFilterParam.getKeyword() != null) {
            query = filterKeyword(diaryFilterParam.getKeyword(), query);
        }

        if (diaryFilterParam.getUserId() != null) {
            query = filterUser(diaryFilterParam.getUserId(), query);
        }

        if (diaryFilterParam.getPlaceId() != null) {
            query = filterPlace(diaryFilterParam.getPlaceId(), query);
        }

        if (diaryFilterParam.getPlaceId() != null) {
            query = filterPlace(diaryFilterParam.getPlaceId(), query);
        }

        if (diaryFilterParam.getCourseId() != null) {
            query = filterCourse(diaryFilterParam.getCourseId(), query);
        }
        return query;
    }

    private JPQLQuery<Diary> filterKeyword(String keyword, JPQLQuery<Diary> query) {
        return query.where(diary.content.contains(keyword));
    }

    private JPQLQuery<Diary> filterUser(Long userId, JPQLQuery<Diary> query) {
        return query.where(diary.coursePlaceRelation.course.user.id.eq(userId));
    }

    private JPQLQuery<Diary> filterPlace(Long placeId, JPQLQuery<Diary> query) {
        return query.where(diary.coursePlaceRelation.place.id.eq(placeId));
    }

    private JPQLQuery<Diary> filterCourse(Long courseId, JPQLQuery<Diary> query) {
        return query.where(diary.coursePlaceRelation.course.id.eq(courseId));
    }


}
