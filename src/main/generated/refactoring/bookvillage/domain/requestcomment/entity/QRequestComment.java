package refactoring.bookvillage.domain.requestcomment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequestComment is a Querydsl query type for RequestComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequestComment extends EntityPathBase<RequestComment> {

    private static final long serialVersionUID = 410389102L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequestComment requestComment = new QRequestComment("requestComment");

    public final StringPath content = createString("content");

    public final StringPath display = createString("display");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final refactoring.bookvillage.domain.member.entity.QMember member;

    public final refactoring.bookvillage.domain.request.entity.QRequest request;

    public QRequestComment(String variable) {
        this(RequestComment.class, forVariable(variable), INITS);
    }

    public QRequestComment(Path<? extends RequestComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRequestComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRequestComment(PathMetadata metadata, PathInits inits) {
        this(RequestComment.class, metadata, inits);
    }

    public QRequestComment(Class<? extends RequestComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new refactoring.bookvillage.domain.member.entity.QMember(forProperty("member")) : null;
        this.request = inits.isInitialized("request") ? new refactoring.bookvillage.domain.request.entity.QRequest(forProperty("request")) : null;
    }

}

