package app.hdj.datepick.domain.diary.repository;

import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import org.springframework.data.domain.Page;

public interface DiaryCustomRepository {

    Page<Diary> findDiaryPage(
        DiaryFilterParam diaryFilterParam, PagingParam pagingParam, CustomSort sort
    );

    Page<Diary> findMyDiaryPage(
        DiaryFilterParam diaryFilterParam, PagingParam pagingParam, CustomSort sort
    );
}
