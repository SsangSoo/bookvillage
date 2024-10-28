package refactoring.bookvillage.domain.borrow.service.command;

import refactoring.bookvillage.domain.borrow.service.dto.commentdto.CreateBorrowCommentDto;
import refactoring.bookvillage.domain.borrow.service.dto.commentdto.UpdateBorrowCommentDto;
import refactoring.bookvillage.domain.borrow.controller.commentdto.BorrowCommentResponse;

public interface BorrowCommentCommandService {

    BorrowCommentResponse create(CreateBorrowCommentDto createBorrowCommentDto);

    BorrowCommentResponse update(UpdateBorrowCommentDto updateBorrowCommentDto);

    void delete(Long borrowId, Long borrowCommentId, Long memberId);

}
