package refactoring.bookvillage.domain.borrow.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.service.BorrowService;

@Slf4j
@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;
    @PostMapping
    public ResponseEntity<BorrowResponseDto> createBorrow(@RequestBody @Valid CreateBorrowRequestDto createBorrowRequestDto, HttpServletRequest request) {
        Long memberId = (Long)request.getAttribute("memberId");
        borrowService.existMember(memberId);
        borrowService.createBorrow(createBorrowRequestDto, memberId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
