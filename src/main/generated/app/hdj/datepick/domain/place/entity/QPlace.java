package app.hdj.datepick.domain.place.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = -798554853L;

    public static final QPlace place = new QPlace("place");

    public final app.hdj.datepick.global.common.entity.QBaseEntity _super = new app.hdj.datepick.global.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> blogReviewCount = createNumber("blogReviewCount", Integer.class);

    public final StringPath category = createString("category");

    public final StringPath contact = createString("contact");

    public final ListPath<app.hdj.datepick.global.common.entity.CoursePlaceRelation, app.hdj.datepick.global.common.entity.QCoursePlaceRelation> coursePlaceRelations = this.<app.hdj.datepick.global.common.entity.CoursePlaceRelation, app.hdj.datepick.global.common.entity.QCoursePlaceRelation>createList("coursePlaceRelations", app.hdj.datepick.global.common.entity.CoursePlaceRelation.class, app.hdj.datepick.global.common.entity.QCoursePlaceRelation.class, PathInits.DIRECT2);

    public final StringPath homepage = createString("homepage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> kakaoId = createNumber("kakaoId", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> pickCount = createNumber("pickCount", Integer.class);

    public final ListPath<PlacePick, QPlacePick> placePicks = this.<PlacePick, QPlacePick>createList("placePicks", PlacePick.class, QPlacePick.class, PathInits.DIRECT2);

    public final NumberPath<Float> rating = createNumber("rating", Float.class);

    public final StringPath region = createString("region");

    public final NumberPath<Integer> reviewCount = createNumber("reviewCount", Integer.class);

    public final StringPath subtype = createString("subtype");

    public final StringPath type = createString("type");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QPlace(String variable) {
        super(Place.class, forVariable(variable));
    }

    public QPlace(Path<? extends Place> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlace(PathMetadata metadata) {
        super(Place.class, metadata);
    }

}

