package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.response.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.response.QPlaceMetaDto;
import app.hdj.datepick.domain.place.entity.PlacePick;
import app.hdj.datepick.domain.place.entity.QPlace;
import app.hdj.datepick.domain.place.entity.QPlacePick;
import app.hdj.datepick.domain.user.dto.QUserMetaDto;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static app.hdj.datepick.domain.place.entity.QPlace.place;
import static app.hdj.datepick.domain.place.entity.QPlacePick.placePick;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public PlaceMetaDto findByIdWithUserPicked(Long id) {

        //TODO security target userId 받아오기
        Long userId = 1L;

        //isPicked 여부 판단
        Long isExistUserId = jpaQueryFactory
                .select(placePick.user.id)
                .from(placePick)
                .where(placePick.user.id.eq(userId), placePick.place.id.eq(id))
                .fetchFirst();
        Boolean isPicked = true;
        if (isExistUserId == null){
            isPicked = false;
        }

        //TODO 결과가 2개 이상이면 NonUniqueResultException 발생
        PlaceMetaDto placeMetaDto = jpaQueryFactory
                .select(new QPlaceMetaDto(
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
                        Expressions.constant(isPicked)
                ))
                .from(place)
                .where(place.id.eq(id))
                .fetchOne();

        if (placeMetaDto == null){
            //TODO exception 아이디에 맞는 장소가 없음.
        }
        return placeMetaDto;
    }
}

