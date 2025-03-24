package refactoring.bookvillage.domain.borrow.service.command;

import refactoring.bookvillage.domain.borrow.controller.response.BorrowResponse;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.UpdateBorrowDto;

public interface BorrowCommandService {

    BorrowResponse create(CreateBorrowDto createBorrowDto);

    BorrowResponse update(UpdateBorrowDto updateBorrowDto);

    void delete(Long borrowId, Long memberId);


}
