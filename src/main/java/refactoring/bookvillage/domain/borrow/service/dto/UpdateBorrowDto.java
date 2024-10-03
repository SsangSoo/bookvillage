package refactoring.bookvillage.domain.borrow.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequestDto;

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