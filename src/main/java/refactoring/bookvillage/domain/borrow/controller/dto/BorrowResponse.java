package refactoring.bookvillage.domain.borrow.controller.dto;


import lombok.Builder;
import lombok.Getter;

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


    @Builder
    public BorrowResponse(Long id, String title, String content, String bookTitle, String author, String publisher, Long viewCount, String thumbnail, boolean writerWhether, boolean isAdmin) {
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
    }
}
