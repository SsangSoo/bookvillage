package refactoring.bookvillage.domain.communitycomment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityComment is a Querydsl query type for CommunityComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityComment extends EntityPathBase<CommunityComment> {

    private static final long serialVersionUID = -2099229830L;

    public static final QCommunityComment communityComment = new QCommunityComment("communityComment");

    public final NumberPath<Long> communityId = createNumber("communityId", Long.class);

    public final StringPath content = createString("content");

    public final StringPath display = createString("display");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QCommunityComment(String variable) {
        super(CommunityComment.class, forVariable(variable));
    }

    public QCommunityComment(Path<? extends CommunityComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityComment(PathMetadata metadata) {
        super(CommunityComment.class, metadata);
    }

}

