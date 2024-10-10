package refactoring.bookvillage.domain.borrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import refactoring.bookvillage.domain.borrow.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {


    @Query("select b.deleteTag from Borrow b where b.id = :borrowId")
    boolean checkDeletedByTag(@Param("borrowId") Long borrowId);
}
