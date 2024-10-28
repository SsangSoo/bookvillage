package refactoring.bookvillage.domain.borrow.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowResponse;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.CreateBorrowDto;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
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
        BorrowResponse responseDto = savedBorrow.toResponseDto(ssangsoo.getId(), ssangsoo.isAdmin());

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