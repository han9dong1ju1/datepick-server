package app.hdj.datepick.domain.place.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlacePick is a Querydsl query type for PlacePick
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlacePick extends EntityPathBase<PlacePick> {

    private static final long serialVersionUID = -1929448484L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlacePick placePick = new QPlacePick("placePick");

    public final app.hdj.datepick.global.common.entity.QBaseTimeEntity _super = new app.hdj.datepick.global.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPlace place;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final app.hdj.datepick.domain.user.entity.QUser user;

    public QPlacePick(String variable) {
        this(PlacePick.class, forVariable(variable), INITS);
    }

    public QPlacePick(Path<? extends PlacePick> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlacePick(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlacePick(PathMetadata metadata, PathInits inits) {
        this(PlacePick.class, metadata, inits);
    }

    public QPlacePick(Class<? extends PlacePick> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.place = inits.isInitialized("place") ? new QPlace(forProperty("place")) : null;
        this.user = inits.isInitialized("user") ? new app.hdj.datepick.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

