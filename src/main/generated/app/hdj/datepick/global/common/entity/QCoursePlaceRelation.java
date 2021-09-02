package app.hdj.datepick.global.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoursePlaceRelation is a Querydsl query type for CoursePlaceRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoursePlaceRelation extends EntityPathBase<CoursePlaceRelation> {

    private static final long serialVersionUID = 509533863L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoursePlaceRelation coursePlaceRelation = new QCoursePlaceRelation("coursePlaceRelation");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final app.hdj.datepick.domain.course.entity.QCourse course;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final app.hdj.datepick.domain.place.entity.QPlace place;

    public final NumberPath<Byte> placeOrder = createNumber("placeOrder", Byte.class);

    public final DateTimePath<java.time.LocalDateTime> visitTime = createDateTime("visitTime", java.time.LocalDateTime.class);

    public QCoursePlaceRelation(String variable) {
        this(CoursePlaceRelation.class, forVariable(variable), INITS);
    }

    public QCoursePlaceRelation(Path<? extends CoursePlaceRelation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoursePlaceRelation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoursePlaceRelation(PathMetadata metadata, PathInits inits) {
        this(CoursePlaceRelation.class, metadata, inits);
    }

    public QCoursePlaceRelation(Class<? extends CoursePlaceRelation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new app.hdj.datepick.domain.course.entity.QCourse(forProperty("course"), inits.get("course")) : null;
        this.place = inits.isInitialized("place") ? new app.hdj.datepick.domain.place.entity.QPlace(forProperty("place")) : null;
    }

}

