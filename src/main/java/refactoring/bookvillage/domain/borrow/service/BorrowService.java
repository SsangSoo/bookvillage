package refactoring.bookvillage.domain.borrow.service;

import refactoring.bookvillage.domain.borrow.controller.dto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowListResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.UpdateBorrowDto;

import java.util.List;

public interface BorrowService {

     void createBorrow(CreateBorrowDto createBorrowDto);

     void updateBorrow(UpdateBorrowDto updateBorrowDto, Long borrowId, Long memberId);

    void deleteBorrow(Long borrowId, Long memberId);

    BorrowResponseDto getBorrow(Long borrowId, Long memberId);

    List<BorrowListResponseDto> getBorrowList(Long memberId, BorrowCondition condition);

}
