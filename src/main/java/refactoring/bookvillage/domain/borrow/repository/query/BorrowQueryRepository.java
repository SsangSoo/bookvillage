package refactoring.bookvillage.domain.borrow.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.repository.query.dto.BorrowListQueryDto;
import refactoring.bookvillage.domain.borrow.repository.query.dto.QBorrowListQueryDto;

import java.util.List;

import static refactoring.bookvillage.domain.borrow.entity.QBorrow.*;

@Repository
public class BorrowQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BorrowQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<BorrowListQueryDto> getBorrowList(String memberRole, BorrowCondition condition) {
        return queryFactory
                .select(new QBorrowListQueryDto(
                        borrow.id,
                        borrow.title,
                        borrow.bookTitle,
                        borrow.author,
                        borrow.publisher,
                        borrow.viewCount,
                        borrow.thumbnail))
                .from(borrow)
//                .where()
                .fetch();
    }

//    private BooleanExpression isAdmin(String memberRole) {
//        if(memberRole.equals(Member.Role.ADMIN)) {
//
//        }

}
