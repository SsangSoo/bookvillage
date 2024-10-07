package refactoring.bookvillage.domain.rate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRate is a Querydsl query type for Rate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRate extends EntityPathBase<Rate> {

    private static final long serialVersionUID = 1503410478L;

    public static final QRate rate = new QRate("rate");

    public final refactoring.bookvillage.domain.audit.QBaseEntity _super = new refactoring.bookvillage.domain.audit.QBaseEntity(this);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final BooleanPath deleteTag = _super.deleteTag;

    public final StringPath display = createString("display");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public QRate(String variable) {
        super(Rate.class, forVariable(variable));
    }

    public QRate(Path<? extends Rate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRate(PathMetadata metadata) {
        super(Rate.class, metadata);
    }

}

