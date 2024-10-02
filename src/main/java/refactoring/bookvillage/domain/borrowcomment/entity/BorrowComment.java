package refactoring.bookvillage.domain.borrowcomment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.audit.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_comment_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "display")
    private String display;

    @Column(name = "member_id")
    private Long member_id;

    @Column(name = "borrow_id")
    private Long borrowId;

}
