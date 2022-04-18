package app.hdj.datepick.domain.diary.controller;


import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.dto.DiaryRequest;
import app.hdj.datepick.domain.diary.dto.DiaryResponse;
import app.hdj.datepick.domain.diary.service.DiaryService;
import app.hdj.datepick.global.annotation.ImageFile;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("")
    CustomPage<DiaryResponse> getDiaryPage(@Valid PagingParam pagingParam,
                                           @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "rating_asc", "rating_desc"}) String sort,
                                           @Valid DiaryFilterParam diaryFilterParam) {
        return diaryService.getDiaryPage(pagingParam, CustomSort.from(sort), diaryFilterParam);
    }

    @GetMapping("/me")
    CustomPage<DiaryResponse> getMyDiaryPage(@AuthenticationPrincipal Long userId,
                                                    @Valid PagingParam pagingParam,
                                                    @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "rating_asc", "rating_desc"}) String sort,
                                                    @Valid DiaryFilterParam diaryFilterParam) {
        diaryFilterParam.setUserId(userId);
        return diaryService.getMyDiaryPage(pagingParam, CustomSort.from(sort), diaryFilterParam);
    }

    @PostMapping("")
    DiaryResponse addDiary(@AuthenticationPrincipal Long userId,
                           @Valid @RequestBody DiaryRequest diaryRequest) {
        return diaryService.addDiary(diaryRequest, userId);
    }

    @GetMapping("/{diaryId}")
    DiaryResponse getDiary(@AuthenticationPrincipal Long userId,
                           @PathVariable Long diaryId) {
        return diaryService.getDiary(diaryId, userId);
    }

    @PatchMapping("/{diaryId}")
    DiaryResponse modifyDiary(@AuthenticationPrincipal Long userId,
                              @PathVariable Long diaryId,
                              @Valid @RequestBody DiaryRequest diaryRequest) {
        return diaryService.modifyDiary(diaryId, diaryRequest, userId);
    }

    @DeleteMapping("/{diaryId}")
    void removeDiary(@AuthenticationPrincipal Long userId,
                     @PathVariable Long diaryId) {
        diaryService.removeDiary(diaryId, userId);
    }

    @PostMapping("/{diaryId}/image")
    ImageUrl addDiaryImage(@AuthenticationPrincipal Long userId,
                           @PathVariable Long diaryId,
                           @NotNull @ImageFile @ModelAttribute MultipartFile image) {
        return diaryService.addDiaryImage(diaryId, image, userId);
    }

    @DeleteMapping("/{diaryId}/image")
    void removeDiaryImage(@AuthenticationPrincipal Long userId,
                          @PathVariable Long diaryId,
                          @NotNull String imageUrl) {
        diaryService.removeDiaryImage(diaryId, imageUrl, userId);
    }
}
