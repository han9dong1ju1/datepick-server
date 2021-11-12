package app.hdj.datepick.domain.diary.controller;


import app.hdj.datepick.domain.diary.dto.DiaryDetailDto;
import app.hdj.datepick.domain.diary.dto.DiaryMetaDto;
import app.hdj.datepick.domain.diary.dto.DiaryModifyRequestDto;
import app.hdj.datepick.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("")
    public Page<DiaryMetaDto> getMyDiaries(Pageable pageable){
        return diaryService.getMyDiaryPage(pageable);
    }

    @GetMapping("/{diaryId}")
    public DiaryDetailDto getMyDiaries(@PathVariable Long diaryId){
        return diaryService.getDiary(diaryId);
    }

    @PostMapping("")
    public DiaryDetailDto createDiary(@RequestBody DiaryModifyRequestDto diaryModifyRequestDto){
        return diaryService.addDiary(diaryModifyRequestDto);
    }

}
