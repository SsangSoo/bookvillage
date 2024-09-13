package refactoring.bookvillage.domain.borrow.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BorrowRepositoryTest {

    @Autowired
    BorrowRepository borrowRepository;

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



    private CreateBorrowDto getCreateBorrowDto() {
        return new CreateBorrowDto(getCreateRequestDto(),1L);
    }

    private CreateBorrowRequestDto getCreateRequestDto() {
        return new CreateBorrowRequestDto("책 제목",
                "책 빌려드립니다.",
                "DDD",
                "에릭 에반스",
                "한빛? 에이콘이었나..?",
                null);
    }

}