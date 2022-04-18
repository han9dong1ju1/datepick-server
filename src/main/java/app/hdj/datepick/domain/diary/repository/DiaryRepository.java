package app.hdj.datepick.domain.diary.repository;

import app.hdj.datepick.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DiaryRepository extends
        JpaRepository<Diary, Long>,
        DiaryCustomRepository,
        QuerydslPredicateExecutor<Diary> {
}
