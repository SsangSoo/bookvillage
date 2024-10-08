package refactoring.bookvillage.domain.borrow.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BorrowRepositoryTest {

    @Autowired
    private BorrowRepository borrowRepository;


    @Test
    @DisplayName("save 테스트 - @Repository 없이 스프링이 알아서 만든 구현체로 확인할 수 있다.")
    void saveTest() {
        // given
        Borrow borrow = Borrow.createBorrow(getCreateBorrowDto());

        // when
        Borrow savedBorrow = borrowRepository.save(borrow);

        // then
        assertThat(savedBorrow.getTitle()).isEqualTo(borrow.getTitle());
    }



    @Test
    @DisplayName("find Test - Jpa는 동일성과 동등성을 보장")
    void findTest() {
        // given
        Borrow borrow = Borrow.createBorrow(getCreateBorrowDto());
        Borrow savedBorrow = borrowRepository.save(borrow);

        // when
        Optional<Borrow> findByBorrow = borrowRepository.findById(savedBorrow.getId());
        Borrow findBorrow = findByBorrow.orElseThrow();

        // then
        assertThat(borrow).isEqualTo(findBorrow);
    }

    @Test
    @DisplayName("게시글 타이틀과 책 제목으로 게시글을 조회할 수 있다.")
    void findBorrowByTitleAndBookTitleTest() {
        // given
        Borrow borrow = Borrow.createBorrow(getCreateBorrowDto());
        Borrow savedBorrow = borrowRepository.save(borrow);

        // when
        Borrow findedBorrow = borrowRepository.findBorrowByTitleAndBookTitle(borrow.getTitle(), borrow.getBookTitle());

        // then
        assertThat(savedBorrow).isEqualTo(findedBorrow);

    }

    @Test
    @DisplayName("삭제 플레그를 통해서 게시글의 삭제여부를 확인할 수 있다.")
    void checkDeletedByTagTest() {
        // given
        Borrow borrow = Borrow.createBorrow(getCreateBorrowDto());
        Borrow savedBorrow = borrowRepository.save(borrow);

        // when
        boolean deletedTag = borrowRepository.checkDeletedByTag(savedBorrow.getId());

        // then
        assertThat(deletedTag).isFalse();

    }



    private CreateBorrowDto getCreateBorrowDto() {
        return CreateBorrowDto.builder()
                .title("책 제목")
                .content("책 빌려드립니다.")
                .author("에릭 에반스")
                .bookTitle("DDD")
                .publisher("한빛? 에이콘이었나..?")
                .thumbnail(null)
                .build();
    }

}