package refactoring.bookvillage.domain.borrowcomment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.audit.BaseEntity;
import refactoring.bookvillage.domain.borrowcomment.service.dto.CreateBorrowCommentDto;
import refactoring.bookvillage.domain.borrowcomment.service.dto.UpdateBorrowCommentDto;
import refactoring.bookvillage.global.exception.BusinessException;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_comment_id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "borrow_id")
    private Long borrowId;



    public void update(String comment) {
        this.comment = comment;
    }


    @Builder
    private BorrowComment(String content, Long memberId, Long borrowId) {
        this.comment = content;
        this.memberId = memberId;
        this.borrowId = borrowId;
    }

    public static BorrowComment createBorrowComment(CreateBorrowCommentDto createBorrowCommentDto) {
        return BorrowComment.builder()
                .content(createBorrowCommentDto.getComment())
                .borrowId(createBorrowCommentDto.getBorrowId())
                .memberId(createBorrowCommentDto.getMemberId())
                .build();
    }

    public void writerValid(Long memberId) {
        if(isOtherWriter(memberId)) {
            throw new BusinessException(BusinessException.ExceptionCode.ACCESS_OTHER_WRITER);
        }
    }

    private boolean isOtherWriter(Long memberId) {
        return !this.memberId.equals(memberId);
    }

    public void borrowValid(Long borrowId) {
        if(isOtherBorrow(borrowId)) {
            throw new BusinessException(BusinessException.ExceptionCode.OTHER_MAIN_CONTENT);
        }

    }

    private boolean isOtherBorrow(Long borrowId) {
        return !this.borrowId.equals(borrowId);
    }


    public void isDeleteValid() {
        if(isDeleteTag()) {
            throw new BusinessException(BusinessException.ExceptionCode.ALREADY_DELETED_COMMENT);
        }
    }
}
