package com.project.chawchaw.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCountry is a Querydsl query type for UserCountry
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserCountry extends EntityPathBase<UserCountry> {

    private static final long serialVersionUID = 140053526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCountry userCountry = new QUserCountry("userCountry");

    public final QCountry country;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath rep = createBoolean("rep");

    public final QUser user;

    public QUserCountry(String variable) {
        this(UserCountry.class, forVariable(variable), INITS);
    }

    public QUserCountry(Path<? extends UserCountry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCountry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCountry(PathMetadata metadata, PathInits inits) {
        this(UserCountry.class, metadata, inits);
    }

    public QUserCountry(Class<? extends UserCountry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

