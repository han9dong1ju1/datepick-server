package app.hdj.datepick.domain.featured.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeatured is a Querydsl query type for Featured
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFeatured extends EntityPathBase<Featured> {

    private static final long serialVersionUID = 416284337L;

    public static final QFeatured featured = new QFeatured("featured");

    public final app.hdj.datepick.global.common.entity.QBaseTimeEntity _super = new app.hdj.datepick.global.common.entity.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final ListPath<app.hdj.datepick.global.common.entity.CourseFeaturedRelation, app.hdj.datepick.global.common.entity.QCourseFeaturedRelation> courseFeaturedRelations = this.<app.hdj.datepick.global.common.entity.CourseFeaturedRelation, app.hdj.datepick.global.common.entity.QCourseFeaturedRelation>createList("courseFeaturedRelations", app.hdj.datepick.global.common.entity.CourseFeaturedRelation.class, app.hdj.datepick.global.common.entity.QCourseFeaturedRelation.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath photoUrl = createString("photoUrl");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFeatured(String variable) {
        super(Featured.class, forVariable(variable));
    }

    public QFeatured(Path<? extends Featured> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFeatured(PathMetadata metadata) {
        super(Featured.class, metadata);
    }

}

