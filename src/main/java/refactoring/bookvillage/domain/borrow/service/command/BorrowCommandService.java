package refactoring.bookvillage.domain.borrow.service.command;

import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowListResponse;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowResponse;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.UpdateBorrowDto;

import java.util.List;

public interface BorrowCommandService {

    BorrowResponse create(CreateBorrowDto createBorrowDto);

    BorrowResponse update(UpdateBorrowDto updateBorrowDto);

    void delete(Long borrowId, Long memberId);


}
