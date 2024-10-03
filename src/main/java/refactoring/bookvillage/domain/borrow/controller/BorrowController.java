package refactoring.bookvillage.domain.borrow.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.service.BorrowService;

@Slf4j
@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity createBorrow(@RequestBody @Valid CreateBorrowRequestDto createBorrowRequestDto,
                                                          HttpServletRequest request) {
        Long memberId = (Long)request.getAttribute("memberId"); // 시큐리티 적용 후 바뀔 예정
        borrowService.createBorrow(createBorrowRequestDto, memberId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{borrowId}")
    public ResponseEntity updateBorrow(@RequestBody @Valid UpdateBorrowRequestDto updateBorrowRequestDto,
                                                          @PathVariable("borrowId") Long borrowId,
                                                          HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        borrowService.updateBorrow(updateBorrowRequestDto, borrowId, memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{borrowId}")
    public ResponseEntity deleteBorrow(@PathVariable("borrowId") Long borrowId, HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        borrowService.deleteBorrow(borrowId, memberId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
