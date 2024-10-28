package refactoring.bookvillage.domain.borrow.service.query;

import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowListResponse;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowResponse;

import java.util.List;

public interface BorrowQueryService {

    BorrowResponse findBorrow(Long borrowId, Long memberId);

    List<BorrowListResponse> findBorrowList(Long memberId, BorrowCondition condition);

}
