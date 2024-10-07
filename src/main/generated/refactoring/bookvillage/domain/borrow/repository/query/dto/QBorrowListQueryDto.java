package refactoring.bookvillage.domain.borrow.repository.query.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * refactoring.bookvillage.domain.borrow.repository.query.dto.QBorrowListQueryDto is a Querydsl Projection type for BorrowListQueryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBorrowListQueryDto extends ConstructorExpression<BorrowListQueryDto> {

    private static final long serialVersionUID = 259092539L;

    public QBorrowListQueryDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> bookTitle, com.querydsl.core.types.Expression<String> author, com.querydsl.core.types.Expression<String> publisher, com.querydsl.core.types.Expression<Long> viewCount, com.querydsl.core.types.Expression<String> thumbnail) {
        super(BorrowListQueryDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, long.class, String.class}, id, title, bookTitle, author, publisher, viewCount, thumbnail);
    }

}

