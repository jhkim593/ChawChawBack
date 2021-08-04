package com.project.chawchaw.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLanguage is a Querydsl query type for UserLanguage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserLanguage extends EntityPathBase<UserLanguage> {

    private static final long serialVersionUID = -1194889512L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLanguage userLanguage = new QUserLanguage("userLanguage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLanguage language;

    public final BooleanPath rep = createBoolean("rep");

    public final QUser user;

    public QUserLanguage(String variable) {
        this(UserLanguage.class, forVariable(variable), INITS);
    }

    public QUserLanguage(Path<? extends UserLanguage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLanguage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLanguage(PathMetadata metadata, PathInits inits) {
        this(UserLanguage.class, metadata, inits);
    }

    public QUserLanguage(Class<? extends UserLanguage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.language = inits.isInitialized("language") ? new QLanguage(forProperty("language")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

