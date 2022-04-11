package app.hdj.datepick.domain.diary.service;

import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.dto.DiaryRequest;
import app.hdj.datepick.domain.diary.dto.DiaryResponse;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class DiaryService {

    public CustomPage<DiaryResponse> getDiaryPage(PagingParam pagingParam,
                                                  CustomSort customSort,
                                                  DiaryFilterParam diaryFilterParam,
                                                  Long userId) {
        return null;
    }

    public DiaryResponse addDiary(DiaryRequest diaryRequest,
                                  Long userId) {
        return null;
    }

    public DiaryResponse getDiary(Long diaryId, Long userId) {
        return null;
    }

    public DiaryResponse modifyDiary(Long diaryId,
                                     DiaryRequest diaryRequest,
                                     Long userId ) {
        return null;
    }

    public void removeDiary(Long diaryId, Long userId) {

    }

    public ImageUrl addDiaryImage(Long diaryId, MultipartFile image, Long userId) {
        return null;
    }

    public void removeDiaryImage(Long diaryId, ImageUrl imageUrl, Long userId) {

    }

}
