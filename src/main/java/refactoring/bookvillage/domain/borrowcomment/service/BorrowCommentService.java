package refactoring.bookvillage.domain.borrowcomment.service;

import refactoring.bookvillage.domain.borrowcomment.controller.dto.BorrowCommentResponse;
import refactoring.bookvillage.domain.borrowcomment.service.dto.CreateBorrowCommentDto;
import refactoring.bookvillage.domain.borrowcomment.service.dto.UpdateBorrowCommentDto;

import java.util.List;

public interface BorrowCommentService {

    BorrowCommentResponse create(CreateBorrowCommentDto createBorrowCommentDto);

    BorrowCommentResponse update(UpdateBorrowCommentDto updateBorrowCommentDto);

    void delete(Long borrowId, Long borrowCommentId, Long memberId);

}
