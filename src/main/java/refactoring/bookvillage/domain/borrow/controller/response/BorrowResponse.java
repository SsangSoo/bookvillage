package refactoring.bookvillage.domain.borrow.controller.response;


import lombok.Builder;
import lombok.Getter;
import refactoring.bookvillage.domain.borrowcomment.controller.response.BorrowCommentResponse;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BorrowResponse {

    private Long id;

    private String title;

    private String content;

    private String bookTitle;

    private String author;

    private String publisher;

    private Long viewCount;

    private String thumbnail;

    private boolean writerWhether;
    private boolean isAdmin;

    private LocalDateTime createdAt;

    private List<BorrowCommentResponse> borrowCommentResponseList;


    @Builder
    private BorrowResponse(Long id, String title, String content, String bookTitle, String author, String publisher, Long viewCount, String thumbnail, boolean writerWhether, boolean isAdmin, LocalDateTime createdAt, List<BorrowCommentResponse> borrowCommentResponseList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.viewCount = viewCount;
        this.thumbnail = thumbnail;
        this.writerWhether = writerWhether;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
        this.borrowCommentResponseList = borrowCommentResponseList;
    }
}
