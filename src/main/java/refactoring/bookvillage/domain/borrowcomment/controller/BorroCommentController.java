package refactoring.bookvillage.domain.borrowcomment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactoring.bookvillage.domain.borrowcomment.controller.dto.CreateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrowcomment.controller.dto.UpdateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrowcomment.service.BorrowCommentService;
import refactoring.bookvillage.global.util.response.MessageResponse;

@Slf4j
@RestController
@RequestMapping("/api/borrow/{borrowId}")
@RequiredArgsConstructor
public class BorroCommentController {

    private final BorrowCommentService borrowCommentService;

    @PostMapping
    public ResponseEntity<Long> createBorrowComment(HttpServletRequest request,
                                              @Valid @RequestBody CreateBorrowCommentRequest createRequestDto,
                                              @PathVariable("borrowId") Long borrowId) {
        Long memberId = (Long) request.getAttribute("memberId");
        Long borrowCommentId = borrowCommentService.create(createRequestDto.requestToServiceDto(memberId, borrowId));
        return new ResponseEntity<>(borrowCommentId, HttpStatus.CREATED);
    }

    @PutMapping("/{borrowCommentId}")
    public ResponseEntity<MessageResponse> updateBorrowComment(HttpServletRequest request,
                                                               @Valid @RequestBody UpdateBorrowCommentRequest updateRequestDto,
                                                               @PathVariable("borrowId") Long borrowId,
                                                               @PathVariable("borrowCommentId") Long borrowCommentId) {
        Long memberId = (Long) request.getAttribute("memberId");
        borrowCommentService.update(updateRequestDto.requestToServiceDto(memberId, borrowId, borrowCommentId));
        return ResponseEntity.ok(new MessageResponse(MessageResponse.MessageCode.BORROW_COMMENT_UPDATED));
    }

    @DeleteMapping("/{borrowCommentId}")
    public ResponseEntity<MessageResponse> deleteBorrowComment(HttpServletRequest request,
                                                               @PathVariable("borrowId") Long borrowId,
                                                               @PathVariable("borrowCommentId") Long borrowCommentId) {
        Long memberId = (Long) request.getAttribute("memberId");
        borrowCommentService.delete(borrowId, borrowCommentId, memberId);
        return ResponseEntity.ok(new MessageResponse(MessageResponse.MessageCode.BORROW_COMMENT_DELETED));
    }
}
