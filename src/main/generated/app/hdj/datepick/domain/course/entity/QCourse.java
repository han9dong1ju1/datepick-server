package app.hdj.datepick.domain.course.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = -1088514421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourse course = new QCourse("course");

    public final app.hdj.datepick.global.common.entity.QBaseTimeEntity _super = new app.hdj.datepick.global.common.entity.QBaseTimeEntity(this);

    public final ListPath<app.hdj.datepick.global.common.entity.CourseFeaturedRelation, app.hdj.datepick.global.common.entity.QCourseFeaturedRelation> courseFeaturedRelations = this.<app.hdj.datepick.global.common.entity.CourseFeaturedRelation, app.hdj.datepick.global.common.entity.QCourseFeaturedRelation>createList("courseFeaturedRelations", app.hdj.datepick.global.common.entity.CourseFeaturedRelation.class, app.hdj.datepick.global.common.entity.QCourseFeaturedRelation.class, PathInits.DIRECT2);

    public final ListPath<app.hdj.datepick.global.common.entity.CoursePlaceRelation, app.hdj.datepick.global.common.entity.QCoursePlaceRelation> coursePlaceRelations = this.<app.hdj.datepick.global.common.entity.CoursePlaceRelation, app.hdj.datepick.global.common.entity.QCoursePlaceRelation>createList("coursePlaceRelations", app.hdj.datepick.global.common.entity.CoursePlaceRelation.class, app.hdj.datepick.global.common.entity.QCoursePlaceRelation.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> expectedAt = createDateTime("expectedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> importCount = createNumber("importCount", Integer.class);

    public final BooleanPath isPrivate = createBoolean("isPrivate");

    public final NumberPath<Integer> pickCount = createNumber("pickCount", Integer.class);

    public final NumberPath<Byte> region = createNumber("region", Byte.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final app.hdj.datepick.domain.user.entity.QUser user;

    public QCourse(String variable) {
        this(Course.class, forVariable(variable), INITS);
    }

    public QCourse(Path<? extends Course> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourse(PathMetadata metadata, PathInits inits) {
        this(Course.class, metadata, inits);
    }

    public QCourse(Class<? extends Course> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new app.hdj.datepick.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

