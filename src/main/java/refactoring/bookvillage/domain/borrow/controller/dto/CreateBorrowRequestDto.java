package refactoring.bookvillage.domain.borrow.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;

@Getter
@AllArgsConstructor
public class CreateBorrowRequestDto {

    @NotBlank(message = "대여 게시글 제목은 필수입니다.")
    private String title;

    @NotBlank(message = "본문 내용은 필수 입니다.")
    private String content;

    @NotBlank(message = "책 제목은 필수입니다.")
    private String bookTitle;

    private String author;

    private String publisher;

    private String thumbnail;

    public CreateBorrowDto createBorrowRequestToServiceDto(Long memberId) {
        return CreateBorrowDto.builder()
                .title(title)
                .content(content)
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .thumbnail(thumbnail)
                .memberId(memberId)
                .build();
    }

}
