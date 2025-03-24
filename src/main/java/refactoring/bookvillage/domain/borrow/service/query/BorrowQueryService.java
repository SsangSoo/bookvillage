package refactoring.bookvillage.domain.borrow.service.query;

import refactoring.bookvillage.domain.borrow.controller.request.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.response.BorrowListResponse;
import refactoring.bookvillage.domain.borrow.controller.response.BorrowResponse;

import java.util.List;

public interface BorrowQueryService {

    BorrowResponse retrieveBorrow(Long borrowId, Long memberId);

    List<BorrowListResponse> retrieveBorrowList(Long memberId, BorrowCondition condition);

}
