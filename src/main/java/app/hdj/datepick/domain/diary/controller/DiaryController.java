package app.hdj.datepick.domain.diary.controller;

import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
import app.hdj.datepick.domain.auth.annotation.Authorize;
import app.hdj.datepick.domain.diary.dto.DiaryFilterParam;
import app.hdj.datepick.domain.diary.dto.DiaryRequest;
import app.hdj.datepick.domain.diary.dto.DiaryResponse;
import app.hdj.datepick.domain.diary.service.DiaryService;
import app.hdj.datepick.domain.user.enums.Role;
import app.hdj.datepick.global.annotation.ImageFile;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("")
    CustomPage<DiaryResponse> getDiaryPage(
        @Valid PagingParam pagingParam,
        @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "rating_asc",
            "rating_desc"}) String sort,
        @Valid DiaryFilterParam diaryFilterParam
    ) {
        return diaryService.getDiaryPage(pagingParam, CustomSort.from(sort), diaryFilterParam);
    }

    @Authorize({Role.USER})
    @GetMapping("/me")
    CustomPage<DiaryResponse> getMyDiaryPage(
        @AuthPrincipal Long userId,
        @Valid PagingParam pagingParam,
        @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "rating_asc",
            "rating_desc"}) String sort,
        @Valid DiaryFilterParam diaryFilterParam
    ) {
        diaryFilterParam.setUserId(userId);
        return diaryService.getMyDiaryPage(pagingParam, CustomSort.from(sort), diaryFilterParam);
    }

    @Authorize({Role.USER})
    @PostMapping("")
    DiaryResponse addDiary(
        @AuthPrincipal Long userId, @Valid @RequestBody DiaryRequest diaryRequest
    ) {
        return diaryService.addDiary(diaryRequest, userId);
    }

    @GetMapping("/{diaryId}")
    DiaryResponse getDiary(
        @AuthPrincipal Long userId, @PathVariable Long diaryId
    ) {
        return diaryService.getDiary(diaryId, userId);
    }

    @Authorize({Role.USER})
    @PatchMapping("/{diaryId}")
    DiaryResponse modifyDiary(
        @AuthPrincipal Long userId,
        @PathVariable Long diaryId,
        @Valid @RequestBody DiaryRequest diaryRequest
    ) {
        return diaryService.modifyDiary(diaryId, diaryRequest, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{diaryId}")
    void removeDiary(
        @AuthPrincipal Long userId, @PathVariable Long diaryId
    ) {
        diaryService.removeDiary(diaryId, userId);
    }

    @Authorize({Role.USER})
    @PostMapping("/{diaryId}/image")
    ImageUrl addDiaryImage(
        @AuthPrincipal Long userId,
        @PathVariable Long diaryId,
        @NotNull @ImageFile @ModelAttribute MultipartFile image
    ) {
        return diaryService.addDiaryImage(diaryId, image, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{diaryId}/image")
    void removeDiaryImage(
        @AuthPrincipal Long userId, @PathVariable Long diaryId, @NotNull String imageUrl
    ) {
        diaryService.removeDiaryImage(diaryId, imageUrl, userId);
    }
}
