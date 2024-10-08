package refactoring.bookvillage.domain.borrowcomment.service;

import refactoring.bookvillage.domain.borrowcomment.service.dto.CreateBorrowCommentDto;

public interface BorrowCommentService {

    void create(CreateBorrowCommentDto createBorrowCommentDto);
}
