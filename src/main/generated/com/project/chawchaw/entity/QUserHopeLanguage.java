package com.project.chawchaw.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserHopeLanguage is a Querydsl query type for UserHopeLanguage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserHopeLanguage extends EntityPathBase<UserHopeLanguage> {

    private static final long serialVersionUID = 1178471476L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserHopeLanguage userHopeLanguage = new QUserHopeLanguage("userHopeLanguage");

    public final QLanguage hopeLanguage;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath rep = createBoolean("rep");

    public final QUser user;

    public QUserHopeLanguage(String variable) {
        this(UserHopeLanguage.class, forVariable(variable), INITS);
    }

    public QUserHopeLanguage(Path<? extends UserHopeLanguage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserHopeLanguage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserHopeLanguage(PathMetadata metadata, PathInits inits) {
        this(UserHopeLanguage.class, metadata, inits);
    }

    public QUserHopeLanguage(Class<? extends UserHopeLanguage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hopeLanguage = inits.isInitialized("hopeLanguage") ? new QLanguage(forProperty("hopeLanguage")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

