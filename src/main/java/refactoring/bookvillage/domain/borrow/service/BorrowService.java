package refactoring.bookvillage.domain.borrow.service;

import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.UpdateBorrowDto;

public interface BorrowService {

     void createBorrow(CreateBorrowDto createBorrowDto);

     void updateBorrow(UpdateBorrowDto updateBorrowDto, Long borrowId, Long memberId);

    void deleteBorrow(Long borrowId, Long memberId);



}
