package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.QPlaceDetailDto;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static app.hdj.datepick.domain.place.entity.QPlace.place;
import static app.hdj.datepick.domain.place.entity.QPlacePick.placePick;
import static app.hdj.datepick.domain.review.entity.QPlaceReview.placeReview;
import static app.hdj.datepick.domain.review.entity.QPlaceReviewPhoto.placeReviewPhoto;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    /**
     *
     * @param placeId 타깃 place id
     * @param userId 타깃 user id
     * @return user가 place를 pick 했는지 여부
     */
    @Override
    public Boolean IsUserPickedPlace(Long placeId, Long userId){
        Long isExistUserId = jpaQueryFactory
                .select(placePick.user.id)
                .from(placePick)
                .where(placePick.user.id.eq(userId), placePick.place.id.eq(placeId))
                .fetchFirst();

        Boolean isPicked = true;
        if (isExistUserId == null){
            isPicked = false;
        }
        return isPicked;
    }

    /**
     *
     * @param placeId 찾을 place id
     * @param isPicked 요청한 user가 타깃 place id를 픽했는지 여부
     * @param photoUrls place에 작성된 review에 등록된 photo url list
     * @return 결과를 조합한 PlaceMetaDto instance
     */
    @Override
    public PlaceDetailDto findPlaceDetail(Long placeId, Boolean isPicked, List<String> photoUrls){
        //TODO 결과가 2개 이상이면 NonUniqueResultException 발생
        PlaceDetailDto placeDetailDto = jpaQueryFactory
                .select(new QPlaceDetailDto(
                        place.id,
                        place.kakaoId,
                        place.name,
                        place.rating,
                        place.address,
                        place.latitude,
                        place.longitude,
                        place.type,
                        place.subtype,
                        place.category,
                        Expressions.constant(isPicked),
                        Expressions.constant(photoUrls)
                ))
                .from(place)
                .where(place.id.eq(placeId))
                .fetchOne();

        if (placeDetailDto == null){
            //TODO exception 아이디에 맞는 장소가 없음.
        }
        return placeDetailDto;
    }

    /**
     *
     * @param placeId 타깃 place id
     * @return place에 속한 review의 photo url을 추출한다
     */
    @Override
    public List<String> findReviewPhotoUrls(Long placeId){
        return jpaQueryFactory
                .select(placeReviewPhoto.photoUrl)
                .from(placeReviewPhoto)
                .leftJoin(placeReviewPhoto.placeReview, placeReview)
                .where(placeReview.place.id.eq(placeId))
                .fetch();
    }
}

