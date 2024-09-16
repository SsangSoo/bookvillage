package refactoring.bookvillage.domain.borrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import refactoring.bookvillage.domain.borrow.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Borrow findBorrowByTitleAndBookTitle(String title, String bookTitle);
}
