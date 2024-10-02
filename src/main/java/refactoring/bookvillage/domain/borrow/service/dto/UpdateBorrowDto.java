package refactoring.bookvillage.domain.borrow.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequestDto;

@Getter
@AllArgsConstructor
public class UpdateBorrowDto {

    private String title;
    private String content;
    private String bookTitle;
    private String author;
    private String publisher;
    private String thumbnail;

    public UpdateBorrowDto(UpdateBorrowRequestDto updateBorrowRequestDto) {
        this.title = updateBorrowRequestDto.getTitle();
        this.content = updateBorrowRequestDto.getContent();
        this.bookTitle = updateBorrowRequestDto.getBookTitle();
        this.author = updateBorrowRequestDto.getAuthor();
        this.publisher = updateBorrowRequestDto.getPublisher();
        this.thumbnail = updateBorrowRequestDto.getThumbnail();
    }
}
