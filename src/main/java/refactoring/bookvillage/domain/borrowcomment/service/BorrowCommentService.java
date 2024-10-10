package refactoring.bookvillage.domain.borrowcomment.service;

import refactoring.bookvillage.domain.borrowcomment.service.dto.CreateBorrowCommentDto;
import refactoring.bookvillage.domain.borrowcomment.service.dto.UpdateBorrowCommentDto;

public interface BorrowCommentService {

    Long create(CreateBorrowCommentDto createBorrowCommentDto);

    void update(UpdateBorrowCommentDto updateBorrowCommentDto);
}
