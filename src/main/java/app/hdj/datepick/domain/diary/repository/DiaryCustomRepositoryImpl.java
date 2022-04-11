package app.hdj.datepick.domain.diary.repository;

import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.global.common.PagingParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


@Slf4j
@RequiredArgsConstructor
@Repository
public class DiaryCustomRepositoryImpl implements DiaryCustomRepository{

    @Override
    public Page<Diary> findDiaryPage(DiaryFilterParam diaryFilterParam, PagingParam pagingParam, Sort sort, Long userId) {
        return null;
    }
}
