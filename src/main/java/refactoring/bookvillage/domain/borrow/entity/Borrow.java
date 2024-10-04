package refactoring.bookvillage.domain.borrow.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.audit.BaseEntity;
import refactoring.bookvillage.domain.borrow.service.dto.UpdateBorrowDto;
import refactoring.bookvillage.global.exception.BusinessException;


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
        return Borrow.builder()
                .title(createBorrowDto.getTitle())
                .content(createBorrowDto.getContent())
                .bookTitle(createBorrowDto.getBookTitle())
                .author(createBorrowDto.getAuthor())
                .publisher(createBorrowDto.getPublisher())
                .thumbnail(createBorrowDto.getThumbnail())
                .memberId(createBorrowDto.getMemberId())
                .build();
    }

    @Builder
    private Borrow(String title, String content, String bookTitle, String author, String publisher, String thumbnail, Long memberId) {
        this.title = title;
        this.content = content;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
        this.memberId = memberId;
    }

    public void update(UpdateBorrowDto updateBorrowDto) {
        this.title = updateBorrowDto.getTitle();
        this.content = updateBorrowDto.getContent();
        this.bookTitle = updateBorrowDto.getBookTitle();
        this.author = updateBorrowDto.getAuthor();
        this.publisher = updateBorrowDto.getPublisher();
        this.thumbnail = updateBorrowDto.getThumbnail();
    }


    public void deleteWhether() {
        if (isDeleteTag()) {
            throw new BusinessException(BusinessException.ExceptionCode.DELETED_CONTENT);
        }
    }

    public void accessOtherWriter(Long memberId) {
        if (nonEquals(memberId)) {
            throw new BusinessException(BusinessException.ExceptionCode.ACCESS_OTHER_WRITER);
        }
    }

    private boolean nonEquals(Long memberId) {
        return !this.memberId.equals(memberId);
    }
}
