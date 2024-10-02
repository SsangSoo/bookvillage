package refactoring.bookvillage.domain.borrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import refactoring.bookvillage.domain.borrow.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Borrow findBorrowByTitleAndBookTitle(String title, String bookTitle);

    boolean existsBorrowById(Long borrowId);

    @Query("select b.deleteTag from Borrow b where b.id = :borrowId")
    boolean checkDeletedByTag(Long borrowId);
}
