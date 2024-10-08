package refactoring.bookvillage.domain.borrow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBorrow is a Querydsl query type for Borrow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBorrow extends EntityPathBase<Borrow> {

    private static final long serialVersionUID = 769597144L;

    public static final QBorrow borrow = new QBorrow("borrow");

    public final refactoring.bookvillage.domain.audit.QBaseEntity _super = new refactoring.bookvillage.domain.audit.QBaseEntity(this);

    public final StringPath author = createString("author");

    public final StringPath bookTitle = createString("bookTitle");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> createdId = _super.createdId;

    //inherited
    public final BooleanPath deleteTag = _super.deleteTag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final NumberPath<Long> modifiedId = _super.modifiedId;

    public final StringPath publisher = createString("publisher");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QBorrow(String variable) {
        super(Borrow.class, forVariable(variable));
    }

    public QBorrow(Path<? extends Borrow> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBorrow(PathMetadata metadata) {
        super(Borrow.class, metadata);
    }

}

