package refactoring.bookvillage.domain.borrow.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactoring.bookvillage.domain.borrow.controller.commentdto.CreateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrow.controller.commentdto.UpdateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrow.service.command.BorrowCommentCommandService;
import refactoring.bookvillage.domain.borrow.controller.commentdto.BorrowCommentResponse;
import refactoring.bookvillage.domain.borrow.service.query.BorrowQueryService;
import refactoring.bookvillage.global.util.response.MessageResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/borrow/{borrowId}")
@RequiredArgsConstructor
public class BorrowCommentController {

    private final BorrowCommentCommandService borrowCommentService;
    private final BorrowQueryService borrowCommentService;

    @PostMapping
    public ResponseEntity<BorrowCommentResponse> createBorrowComment(HttpServletRequest request,
                                              @Valid @RequestBody CreateBorrowCommentRequest createRequestDto,
                                              @PathVariable("borrowId") Long borrowId) {
        Long memberId = (Long) request.getAttribute("memberId");
        BorrowCommentResponse borrowCommentResponse = borrowCommentService.create(createRequestDto.requestToServiceDto(memberId, borrowId));
        return new ResponseEntity<>(borrowCommentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{borrowCommentId}")
    public ResponseEntity<BorrowCommentResponse> updateBorrowComment(HttpServletRequest request,
                                                               @Valid @RequestBody UpdateBorrowCommentRequest updateRequestDto,
                                                               @PathVariable("borrowId") Long borrowId,
                                                               @PathVariable("borrowCommentId") Long borrowCommentId) {
        Long memberId = (Long) request.getAttribute("memberId");
        BorrowCommentResponse borrowCommentResponse = borrowCommentService.update(updateRequestDto.requestToServiceDto(memberId, borrowId, borrowCommentId));
        return ResponseEntity.ok(borrowCommentResponse);
    }

    @DeleteMapping("/{borrowCommentId}")
    public ResponseEntity<MessageResponse> deleteBorrowComment(HttpServletRequest request,
                                                               @PathVariable("borrowId") Long borrowId,
                                                               @PathVariable("borrowCommentId") Long borrowCommentId) {
        Long memberId = (Long) request.getAttribute("memberId");
        borrowCommentService.delete(borrowId, borrowCommentId, memberId);
        return ResponseEntity.ok(new MessageResponse(MessageResponse.MessageCode.BORROW_COMMENT_DELETED));
    }

    @GetMapping
    public ResponseEntity<List<BorrowCommentResponse>> getBorrowComments(HttpServletRequest request,
                                                                         @PathVariable("borrowId") Long borrowId) {
        Long memberId = (Long) request.getAttribute("memberId");
        List<BorrowCommentResponse> borrowCommentResponseList = borrowCommentService.getBorrowComments(borrowId, memberId);
        return ResponseEntity.ok(borrowCommentResponseList);

    }
}