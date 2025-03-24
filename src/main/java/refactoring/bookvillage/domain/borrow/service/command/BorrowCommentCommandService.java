package refactoring.bookvillage.domain.borrow.service.command;

import refactoring.bookvillage.domain.borrow.service.dto.commentdto.CreateBorrowCommentDto;
import refactoring.bookvillage.domain.borrow.service.dto.commentdto.UpdateBorrowCommentDto;
import refactoring.bookvillage.domain.borrowcomment.controller.response.BorrowCommentResponse;

import java.util.List;

public interface BorrowCommentCommandService {

    BorrowCommentResponse registerBorrowComment(CreateBorrowCommentDto createBorrowCommentDto);

    BorrowCommentResponse modifyBorrowComment(UpdateBorrowCommentDto updateBorrowCommentDto);

    void removeBorrowComment(Long borrowId, Long borrowCommentId, Long memberId);

    List<BorrowCommentResponse> retrieveBorrowComments(Long borrowId, Long memberId);

}
