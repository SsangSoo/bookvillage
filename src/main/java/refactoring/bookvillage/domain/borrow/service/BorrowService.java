package refactoring.bookvillage.domain.borrow.service;

import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequest;

public interface BorrowService {

     void createBorrow(CreateBorrowRequestDto createBorrowRequestDto, Long memberId);

     void updateBorrow(UpdateBorrowRequest updateBorrowRequestDto, Long borrowId, Long memberId);

     void existMember(Long memberId);

     void existBorrow(Long borrowId);

}
