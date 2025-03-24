package refactoring.bookvillage.domain.borrowcomment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactoring.bookvillage.domain.borrowcomment.controller.request.RegisterBorrowCommentRequest;
import refactoring.bookvillage.domain.borrowcomment.controller.request.UpdateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrow.service.command.BorrowCommentCommandService;
import refactoring.bookvillage.domain.borrowcomment.controller.response.BorrowCommentResponse;
import refactoring.bookvillage.domain.borrow.service.query.BorrowQueryService;
import refactoring.bookvillage.global.util.response.MessageResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/borrow/{borrowId}")
@RequiredArgsConstructor
public class BorrowCommentController {

    private final BorrowQueryService borrowQueryService;
    private final BorrowCommentCommandService borrowCommentCommandService;

    @PostMapping
    public ResponseEntity<BorrowCommentResponse> registerBorrowComment(
            HttpServletRequest request,
            @Valid @RequestBody RegisterBorrowCommentRequest registerBorrowCommentRequest,
            @PathVariable("borrowId") Long borrowId) {
        Long memberId = (Long) request.getAttribute("memberId");
        BorrowCommentResponse borrowCommentResponse = borrowCommentCommandService.registerBorrowComment(registerBorrowCommentRequest.toServiceRequest(memberId, borrowId));
        return new ResponseEntity<>(borrowCommentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{borrowCommentId}")
    public ResponseEntity<BorrowCommentResponse> modifyBorrowComment(
            HttpServletRequest request,
            @Valid @RequestBody UpdateBorrowCommentRequest updateRequestDto,
            @PathVariable("borrowId") Long borrowId,
            @PathVariable("borrowCommentId") Long borrowCommentId) {
        Long memberId = (Long) request.getAttribute("memberId");
        BorrowCommentResponse borrowCommentResponse = borrowCommentCommandService.modifyBorrowComment(updateRequestDto.requestToServiceDto(memberId, borrowId, borrowCommentId));
        return ResponseEntity.ok(borrowCommentResponse);
    }

    @DeleteMapping("/{borrowCommentId}")
    public ResponseEntity<MessageResponse> removeBorrowComment(
            HttpServletRequest request,
            @PathVariable("borrowId") Long borrowId,
            @PathVariable("borrowCommentId") Long borrowCommentId) {
        Long memberId = (Long) request.getAttribute("memberId");
        borrowCommentCommandService.removeBorrowComment(borrowId, borrowCommentId, memberId);
        return ResponseEntity.ok(new MessageResponse(MessageResponse.MessageCode.BORROW_COMMENT_DELETED));
    }

    @GetMapping
    public ResponseEntity<List<BorrowCommentResponse>> retrieveBorrowComments(
            HttpServletRequest request,
            @PathVariable("borrowId") Long borrowId) {
        Long memberId = (Long) request.getAttribute("memberId");
        List<BorrowCommentResponse> borrowCommentResponseList = borrowCommentCommandService.retrieveBorrowComments(borrowId, memberId);
        return ResponseEntity.ok(borrowCommentResponseList);

    }
}
