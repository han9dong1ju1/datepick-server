package app.hdj.datepick.global.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourseFeaturedRelation is a Querydsl query type for CourseFeaturedRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCourseFeaturedRelation extends EntityPathBase<CourseFeaturedRelation> {

    private static final long serialVersionUID = 508876614L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseFeaturedRelation courseFeaturedRelation = new QCourseFeaturedRelation("courseFeaturedRelation");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final app.hdj.datepick.domain.course.entity.QCourse course;

    public final NumberPath<Byte> courseOrder = createNumber("courseOrder", Byte.class);

    public final app.hdj.datepick.domain.featured.entity.QFeatured featured;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCourseFeaturedRelation(String variable) {
        this(CourseFeaturedRelation.class, forVariable(variable), INITS);
    }

    public QCourseFeaturedRelation(Path<? extends CourseFeaturedRelation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourseFeaturedRelation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourseFeaturedRelation(PathMetadata metadata, PathInits inits) {
        this(CourseFeaturedRelation.class, metadata, inits);
    }

    public QCourseFeaturedRelation(Class<? extends CourseFeaturedRelation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new app.hdj.datepick.domain.course.entity.QCourse(forProperty("course"), inits.get("course")) : null;
        this.featured = inits.isInitialized("featured") ? new app.hdj.datepick.domain.featured.entity.QFeatured(forProperty("featured")) : null;
    }

}

