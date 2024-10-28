package refactoring.bookvillage.domain.borrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import refactoring.bookvillage.domain.borrow.entity.BorrowComment;

public interface BorrowCommentRepository extends JpaRepository<BorrowComment, Long> {
}
