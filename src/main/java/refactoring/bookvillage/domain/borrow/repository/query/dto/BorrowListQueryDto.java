package refactoring.bookvillage.domain.borrow.repository.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowListResponse;

@NoArgsConstructor
public class BorrowListQueryDto {

    private Long id;
    private String title;
    private String bookTitle;
    private String author;
    private String publisher;
    private Long viewCount;
    private String thumbnail;

    @Builder
    @QueryProjection
    public BorrowListQueryDto(Long id, String title, String bookTitle, String author, String publisher, Long viewCount, String thumbnail) {
        this.id = id;
        this.title = title;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.viewCount = viewCount;
        this.thumbnail = thumbnail;
    }

    public BorrowListResponse toResponseDto() {
        return BorrowListResponse.builder()
                .id(id)
                .title(title)
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .viewCount(viewCount)
                .thumbnail(thumbnail)
                .build();
    }


}
