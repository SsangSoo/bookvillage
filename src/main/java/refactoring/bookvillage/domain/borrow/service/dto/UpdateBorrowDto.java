package refactoring.bookvillage.domain.borrow.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateBorrowDto {

    private String title;
    private String content;
    private String bookTitle;
    private String author;
    private String publisher;
    private String thumbnail;

    @Builder
    public UpdateBorrowDto(String title, String content, String bookTitle, String author, String publisher, String thumbnail) {
        this.title = title;
        this.content = content;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
    }
}