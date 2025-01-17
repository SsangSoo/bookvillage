package refactoring.bookvillage.domain.borrowcomment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import refactoring.bookvillage.domain.borrow.entity.BorrowComment;


/**
 * QBorrowComment is a Querydsl query type for BorrowComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBorrowComment extends EntityPathBase<BorrowComment> {

    private static final long serialVersionUID = 172602164L;

    public static final QBorrowComment borrowComment = new QBorrowComment("borrowComment");

    public final refactoring.bookvillage.domain.audit.QBaseEntity _super = new refactoring.bookvillage.domain.audit.QBaseEntity(this);

    public final NumberPath<Long> borrowId = createNumber("borrowId", Long.class);

    public final StringPath comment = createString("comment");

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

    public QBorrowComment(String variable) {
        super(BorrowComment.class, forVariable(variable));
    }

    public QBorrowComment(Path<? extends BorrowComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBorrowComment(PathMetadata metadata) {
        super(BorrowComment.class, metadata);
    }

}

