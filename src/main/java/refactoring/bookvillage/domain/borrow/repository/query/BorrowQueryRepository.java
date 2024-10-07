package refactoring.bookvillage.domain.borrow.repository.query;

import refactoring.bookvillage.domain.borrow.controller.dto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.repository.query.dto.BorrowListQueryDto;

import java.util.List;

public interface BorrowQueryRepository {


    List<BorrowListQueryDto> getBorrowList(String memberRole, BorrowCondition condition);
}
