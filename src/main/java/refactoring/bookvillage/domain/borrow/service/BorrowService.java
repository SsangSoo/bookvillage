package refactoring.bookvillage.domain.borrow.service;

import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;

public interface BorrowService {

     void createBorrow(CreateBorrowRequestDto createBorrowRequestDto, Long memberId);

     void existMember(Long memberId);
}
