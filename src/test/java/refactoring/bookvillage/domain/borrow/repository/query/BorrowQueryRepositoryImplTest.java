package refactoring.bookvillage.domain.borrow.repository.query;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import refactoring.bookvillage.configuration.TestQueryDslConfig;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.repository.query.dto.BorrowListQueryDto;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Import(TestQueryDslConfig.class)
@DataJpaTest
class BorrowQueryRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BorrowQueryRepository borrowQueryRepository;


    @Test
    @DisplayName("대여 게시글 목록을 조회할 수 있다.")
    void getBorrowListTest() {
        //given

        Borrow dddBorrow = Borrow.createBorrow(getCreateBorrowDto(1L, "DDD", "도메인 주도 개발 대여해드립니다."));
        Borrow caBorrow = Borrow.createBorrow(getCreateBorrowDto(2L, "클린 아키텍처", "클린 아키텍처 빌려드립니다."));
        Borrow rmBorrow = Borrow.createBorrow(getCreateBorrowDto(3L, "Real MySql 1", "Real MySql 1 빌려드립니다."));

        borrowRepository.saveAll(List.of(dddBorrow, caBorrow, rmBorrow));

        //when
        List<BorrowListQueryDto> borrowList = borrowQueryRepository.getBorrowList(null, null);

        //then
        assertThat(borrowList).hasSize(3);
        assertThat(borrowList).extracting("bookTitle")
                .containsExactlyInAnyOrder("DDD", "클린 아키텍처", "Real MySql 1");

    }



    private CreateBorrowDto getCreateBorrowDto(Long memberId, String bookTitle, String title) {
        return CreateBorrowDto.builder()
                .title(title)
                .content("책 빌려드립니다.")
                .author("에릭 에반스")
                .bookTitle(bookTitle)
                .publisher("한빛? 에이콘이었나..?")
                .thumbnail(null)
                .memberId(memberId)
                .build();
    }
}