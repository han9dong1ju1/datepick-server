package app.hdj.datepick.domain.review.repository;

import app.hdj.datepick.domain.review.dto.PlaceReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceReviewCustomRepository {


    /**
     *
     * @param placeId 찾아올 review를 포함한 place Id
     * @return Place Detail에 보여줄 특정개수의 Place Review List를 제공
     */
    List<PlaceReviewDto> findConstReviewsWithPlaceId(Long placeId);

    /**
     *
     * @param placeId Photo Url을 포함할 Place Id
     * @param pageable Paging 정보
     * @return Place에 포함된 Photo Url의 Page 객체
     */
    Page<String> findAllPhotoUrls(Long placeId, Pageable pageable);
}
