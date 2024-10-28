package refactoring.bookvillage.domain.borrow.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.*;
import refactoring.bookvillage.domain.borrow.service.command.BorrowCommandService;
import refactoring.bookvillage.global.util.response.MessageResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowCommandService borrowService;

    @PostMapping
    public ResponseEntity<BorrowResponse> createBorrow(@RequestBody @Valid CreateBorrowRequest createRequestDto,
                                                          HttpServletRequest request) {
        Long memberId = (Long)request.getAttribute("memberId"); // 시큐리티 적용 후 바뀔 예정
        BorrowResponse borrowResponse = borrowService.create(createRequestDto.createRequestToServiceDto(memberId));
        return new ResponseEntity<>(borrowResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{borrowId}")
    public ResponseEntity<BorrowResponse> updateBorrow(@RequestBody @Valid UpdateBorrowRequest updateRequestDto,
                                                        @PathVariable("borrowId") Long borrowId,
                                                        HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        BorrowResponse borrowResponse = borrowService.update(updateRequestDto.updateRequestToServiceDto(borrowId, memberId));
        return new ResponseEntity<>(borrowResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{borrowId}")
    public ResponseEntity<MessageResponse> deleteBorrow(@PathVariable("borrowId") Long borrowId, HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        borrowService.delete(borrowId, memberId);
        return new ResponseEntity<>(new MessageResponse(MessageResponse.MessageCode.BORROW_DELETED), HttpStatus.NO_CONTENT);
    }


    // 게시글 작성자인지 아닌지에 따라 프론트에서 수정, 삭제 버튼이 보이도록 한다.
        // 추후 관리자일 경우 숨김 처리나 삭제처리를 할 수 있도록 관리자인지에 대한 정보도 알려준다.
    @GetMapping("/{borrowId}")
    public ResponseEntity<BorrowResponse> getBorrow(@PathVariable("borrowId") Long borrowId, HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        return ResponseEntity.ok(borrowService.findBorrow(borrowId, memberId));
    }

    // 대여 목록을 받아온다.
        // 관리자라면 삭제 태그가 붙은 것도 포함하여 보여준다.
        // 그냥 단순 멤버라면 삭제 태그가 붙지 않은 것만 보여준다.
    @GetMapping
    public ResponseEntity<List<BorrowListResponse>> getBorrowList(HttpServletRequest request, BorrowCondition condition) {
        Long memberId = (Long) request.getAttribute("memberId");
        return ResponseEntity.ok(borrowService.findBorrowList(memberId, condition));
    }



}
