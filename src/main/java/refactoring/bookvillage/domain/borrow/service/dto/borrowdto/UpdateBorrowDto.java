package refactoring.bookvillage.domain.borrow.service.dto.borrowdto;

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
    private Long borrowId;
    private Long memberId;

    @Builder
    public UpdateBorrowDto(String title, String content, String bookTitle, String author, String publisher, String thumbnail, Long borrowId, Long memberId) {
        this.title = title;
        this.content = content;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
        this.borrowId = borrowId;
        this.memberId = memberId;
    }
}