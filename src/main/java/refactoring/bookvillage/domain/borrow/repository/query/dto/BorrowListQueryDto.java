package refactoring.bookvillage.domain.borrow.repository.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.borrow.controller.response.BorrowListResponse;

import java.time.LocalDateTime;

@NoArgsConstructor
public class BorrowListQueryDto {

    private Long id;
    private String title;
    private String bookTitle;
    private String author;
    private String publisher;
    private Long viewCount;
    private String thumbnail;
    private LocalDateTime createdAt;

    @Builder
    @QueryProjection
    public BorrowListQueryDto(Long id, String title, String bookTitle, String author, String publisher, Long viewCount, String thumbnail, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.viewCount = viewCount;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
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
                .createAt(createdAt)
                .build();
    }


}
