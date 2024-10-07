package refactoring.bookvillage.domain.borrow.controller.dto;

import lombok.Builder;

public class BorrowListResponseDto {
    private Long id;
    private String title;
    private String bookTitle;
    private String author;
    private String publisher;
    private Long viewCount;
    private String thumbnail;

    @Builder
    public BorrowListResponseDto(Long id, String title, String bookTitle, String author, String publisher, Long viewCount, String thumbnail) {
        this.id = id;
        this.title = title;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.viewCount = viewCount;
        this.thumbnail = thumbnail;
    }
}
