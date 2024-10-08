package refactoring.bookvillage.domain.borrowcomment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import refactoring.bookvillage.domain.borrowcomment.controller.dto.CreateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrowcomment.service.BorrowCommentService;

@Slf4j
@RestController
@RequestMapping("/api/borrow/{borrowId}")
@RequiredArgsConstructor
public class BorroCommentController {

    private final BorrowCommentService borrowCommentService;

    public ResponseEntity createBorrowComment(HttpServletRequest request,
                                              @Valid @RequestBody CreateBorrowCommentRequest createRequestDto,
                                              @PathVariable("borrowId") Long borrowId) {
//        borrowCommentService.create();

        return null;
    }
}
