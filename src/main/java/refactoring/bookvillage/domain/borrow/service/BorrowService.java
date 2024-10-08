package refactoring.bookvillage.domain.borrow.service;

import refactoring.bookvillage.domain.borrow.controller.dto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowListResponse;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponse;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.UpdateBorrowDto;

import java.util.List;

public interface BorrowService {

     void create(CreateBorrowDto createBorrowDto);

     void update(UpdateBorrowDto updateBorrowDto, Long borrowId, Long memberId);

    void delete(Long borrowId, Long memberId);

    BorrowResponse findOne(Long borrowId, Long memberId);

    List<BorrowListResponse> findList(Long memberId, BorrowCondition condition);

}
