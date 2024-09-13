package refactoring.bookvillage.domain.rate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.book.entity.Book;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.global.audit.BaseEntity;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    @Column(name = "score")
    private int score;

    @Column(name = "content")
    private String content;

    @Column(name = "display", length = 100)
    private String display;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "member_id")
    private Long memberId;
}
