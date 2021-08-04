package com.project.chawchaw.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QView is a Querydsl query type for View
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QView extends EntityPathBase<View> {

    private static final long serialVersionUID = 691545658L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QView view = new QView("view");

    public final QUser fromUser;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser toUser;

    public QView(String variable) {
        this(View.class, forVariable(variable), INITS);
    }

    public QView(Path<? extends View> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QView(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QView(PathMetadata metadata, PathInits inits) {
        this(View.class, metadata, inits);
    }

    public QView(Class<? extends View> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromUser = inits.isInitialized("fromUser") ? new QUser(forProperty("fromUser")) : null;
        this.toUser = inits.isInitialized("toUser") ? new QUser(forProperty("toUser")) : null;
    }

}

