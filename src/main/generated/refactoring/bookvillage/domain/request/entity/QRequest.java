package refactoring.bookvillage.domain.request.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequest is a Querydsl query type for Request
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequest extends EntityPathBase<Request> {

    private static final long serialVersionUID = -312645388L;

    public static final QRequest request = new QRequest("request");

    public final StringPath content = createString("content");

    public final StringPath display = createString("display");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QRequest(String variable) {
        super(Request.class, forVariable(variable));
    }

    public QRequest(Path<? extends Request> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequest(PathMetadata metadata) {
        super(Request.class, metadata);
    }

}

