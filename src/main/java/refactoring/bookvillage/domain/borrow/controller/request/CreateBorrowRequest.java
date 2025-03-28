package refactoring.bookvillage.domain.borrow.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.CreateBorrowDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBorrowRequest {

    @NotBlank(message = "대여 게시글 제목은 필수입니다.")
    private String title;

    @NotBlank(message = "본문 내용은 필수 입니다.")
    private String content;

    @NotBlank(message = "책 제목은 필수입니다.")
    private String bookTitle;

    private String author;

    private String publisher;

    private String thumbnail;


    public CreateBorrowDto createRequestToServiceDto(Long memberId) {
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
