package app.hdj.datepick.domain.diary.repository;


import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.global.common.PagingParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface DiaryCustomRepository{
    Page<Diary> findDiaryPage(DiaryFilterParam diaryFilterParam, PagingParam pagingParam, Sort sort);
    Page<Diary> findMyDiaryPage(DiaryFilterParam diaryFilterParam, PagingParam pagingParam, Sort sort);
}
