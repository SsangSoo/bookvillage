package refactoring.bookvillage.domain.borrow.service;

import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequestDto;

public interface BorrowService {

     void createBorrow(CreateBorrowRequestDto createBorrowRequestDto, Long memberId);

     void updateBorrow(UpdateBorrowRequestDto updateBorrowRequestDto, Long borrowId, Long memberId);

    void deleteBorrow(Long borrowId, Long memberId);

    void existMember(Long memberId);

}
