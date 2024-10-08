package refactoring.bookvillage.domain.borrow.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import refactoring.bookvillage.domain.borrow.repository.query.dto.BorrowListQueryDto;
import refactoring.bookvillage.domain.borrow.repository.query.dto.QBorrowListQueryDto;
import refactoring.bookvillage.domain.member.entity.Member;

import java.util.List;
import java.util.Objects;

import static refactoring.bookvillage.domain.borrow.entity.QBorrow.*;

@Repository
public class BorrowQueryRepositoryImpl implements BorrowQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BorrowQueryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<BorrowListQueryDto> getBorrowList(String memberRole, String keyword) {
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
                .where(searchKeyword(keyword), isAdmin(memberRole))
                .fetch();
    }

    private BooleanExpression isAdmin(String memberRole) {
        if(memberRole.equals(Member.Role.ADMIN.name())) {
            return null;
        }
        return borrow.deleteTag.eq(false);
    }

    private BooleanExpression searchKeyword(String keyword) {
        if(Objects.isNull(keyword)) {
            return null;
        }

        return borrow.title.contains(keyword)
                .or(borrow.bookTitle.contains(keyword))
                .or(borrow.author.contains(keyword))
                .or(borrow.content.contains(keyword))
                .or(borrow.publisher.contains(keyword));

    }

}
