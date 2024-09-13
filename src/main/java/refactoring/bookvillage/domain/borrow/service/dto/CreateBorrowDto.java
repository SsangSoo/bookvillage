package refactoring.bookvillage.domain.borrow.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBorrowDto {

    private String title;
    private String content;
    private String bookTitle;
    private String author;
    private String publisher;
    private String thumbnail;
    private Long memberId;

}
