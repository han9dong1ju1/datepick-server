package app.hdj.datepick.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1827028971L;

    public static final QUser user = new QUser("user");

    public final app.hdj.datepick.global.common.entity.QBaseTimeEntity _super = new app.hdj.datepick.global.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ComparablePath<Character> gender = createComparable("gender", Character.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final ListPath<app.hdj.datepick.domain.place.entity.PlacePick, app.hdj.datepick.domain.place.entity.QPlacePick> placePicks = this.<app.hdj.datepick.domain.place.entity.PlacePick, app.hdj.datepick.domain.place.entity.QPlacePick>createList("placePicks", app.hdj.datepick.domain.place.entity.PlacePick.class, app.hdj.datepick.domain.place.entity.QPlacePick.class, PathInits.DIRECT2);

    public final StringPath profileUrl = createString("profileUrl");

    public final StringPath uid = createString("uid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

