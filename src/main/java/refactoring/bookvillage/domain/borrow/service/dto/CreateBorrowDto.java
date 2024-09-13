package refactoring.bookvillage.domain.borrow.service.dto;

import lombok.Getter;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;

@Getter
public class CreateBorrowDto {

    private String title;
    private String content;
    private String bookTitle;
    private String author;
    private String publisher;
    private String thumbnail;
    private Long memberId;

    public CreateBorrowDto(CreateBorrowRequestDto createBorrowDto, Long memberId) {
        this.title = createBorrowDto.getTitle();
        this.content = createBorrowDto.getContent();
        this.bookTitle = createBorrowDto.getBookTitle();
        this.author = createBorrowDto.getAuthor();
        this.publisher = createBorrowDto.getPublisher();
        this.thumbnail = createBorrowDto.getThumbnail();
        this.memberId = memberId;
    }

}
