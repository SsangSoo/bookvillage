package refactoring.bookvillage.domain.borrow.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
class BorrowTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BorrowRepository borrowRepository;

    @Test
    @DisplayName("도메인 객체로 응답값을 반환한다.")
    void toResponseDtoTest() {
        //given
        Member ssangsoo = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));

        Borrow borrow = Borrow.createBorrow(getCreateBorrowDto(ssangsoo.getId()));
        Borrow savedBorrow = borrowRepository.save(borrow);

        //when
        BorrowResponseDto responseDto = savedBorrow.toResponseDto(ssangsoo.getId(), ssangsoo.getRole().name());

        //then
        assertThat(responseDto.isAdmin()).isFalse();
        assertThat(responseDto.isWriterWhether()).isTrue();
    }



    private CreateBorrowDto getCreateBorrowDto(Long memberId) {
        return CreateBorrowDto.builder()
                .title("책 제목")
                .content("책 빌려드립니다.")
                .author("에릭 에반스")
                .bookTitle("DDD")
                .publisher("한빛? 에이콘이었나..?")
                .thumbnail(null)
                .memberId(memberId)
                .build();
    }
}