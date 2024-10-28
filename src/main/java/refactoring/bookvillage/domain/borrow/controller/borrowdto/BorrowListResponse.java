package refactoring.bookvillage.domain.borrow.controller.borrowdto;

import lombok.Builder;

import java.time.LocalDateTime;

public class BorrowListResponse {
    private Long id;
    private String title;
    private String bookTitle;
    private String author;
    private String publisher;
    private Long viewCount;
    private String thumbnail;
    private LocalDateTime createAt;

    @Builder
    private BorrowListResponse(Long id, String title, String bookTitle, String author, String publisher, Long viewCount, String thumbnail, LocalDateTime createAt) {
        this.id = id;
        this.title = title;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.viewCount = viewCount;
        this.thumbnail = thumbnail;
        this.createAt = createAt;

    }
}
