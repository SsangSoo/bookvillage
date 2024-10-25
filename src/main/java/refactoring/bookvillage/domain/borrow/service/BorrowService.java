package refactoring.bookvillage.domain.borrow.service;

import refactoring.bookvillage.domain.borrow.controller.dto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowListResponse;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponse;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.UpdateBorrowDto;

import java.util.List;

public interface BorrowService {

    BorrowResponse create(CreateBorrowDto createBorrowDto);

    BorrowResponse update(UpdateBorrowDto updateBorrowDto);

    void delete(Long borrowId, Long memberId);

    BorrowResponse findBorrow(Long borrowId, Long memberId);

    List<BorrowListResponse> findBorrowList(Long memberId, BorrowCondition condition);

}
