package refactoring.bookvillage.domain.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = 1479823296L;

    public static final QBook book = new QBook("book");

    public final refactoring.bookvillage.domain.audit.QBaseEntity _super = new refactoring.bookvillage.domain.audit.QBaseEntity(this);

    public final StringPath author = createString("author");

    public final NumberPath<Double> average = createNumber("average", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final BooleanPath deleteTag = _super.deleteTag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath isbn = createString("isbn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath publisher = createString("publisher");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata metadata) {
        super(Book.class, metadata);
    }

}

