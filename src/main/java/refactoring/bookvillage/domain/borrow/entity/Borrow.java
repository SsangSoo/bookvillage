package refactoring.bookvillage.domain.borrow.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.global.audit.BaseEntity;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Borrow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "book_title", length = 100)
    private String bookTitle;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "member_id")
    private Long memberId;

    public static Borrow createBorrow(CreateBorrowDto createBorrowDto) {
        return new Borrow(createBorrowDto);
    }

    private Borrow(CreateBorrowDto createBorrowDto) {
        this.title = createBorrowDto.getTitle();
        this.content = createBorrowDto.getContent();
        this.bookTitle = createBorrowDto.getBookTitle();
        this.author = createBorrowDto.getAuthor();
        this.publisher = createBorrowDto.getPublisher();
        this.thumbnail = createBorrowDto.getThumbnail();
        this.memberId = createBorrowDto.getMemberId();
    }


}
