package refactoring.bookvillage.domain.borrowcomment.repository.query;

import refactoring.bookvillage.domain.borrowcomment.repository.query.dto.BorrowCommentListQueryDto;

import java.util.List;

public interface BorrowCommentQueryRepository {

    List<BorrowCommentListQueryDto> getBorrowCommentList(String memberRole, String keyword);

}