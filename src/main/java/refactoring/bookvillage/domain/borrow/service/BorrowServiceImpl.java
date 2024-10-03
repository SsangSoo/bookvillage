package refactoring.bookvillage.domain.borrow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.UpdateBorrowDto;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createBorrow(CreateBorrowRequestDto createBorrowRequestDto, Long memberId) {
        existMember(memberId);
        Borrow borrow = createDtoToEntity(createBorrowRequestDto, memberId);
        borrowRepository.save(borrow);
    }

    @Override
    public void updateBorrow(UpdateBorrowRequestDto updateBorrowRequestDto, Long borrowId, Long memberId) {
        existMember(memberId);
        Optional<Borrow> findBorrow = borrowRepository.findById(borrowId);
        Borrow borrow = findBorrow.orElseThrow(() -> new BusinessException("대여 게시글이 존재하지 않습니다."));
        validationBeforeUpdate(memberId, borrow);
        borrow.update(updateRequestToUpdateDto(updateBorrowRequestDto));
    }

    @Override
    public void deleteBorrow(Long borrowId, Long memberId) {

    }

    private void validationBeforeUpdate(Long memberId, Borrow borrow) {
        if(borrow.isDeleteTag()) {
            throw new BusinessException("삭제된 대여 게시글입니다.");
        }

        if (!borrow.getMemberId().equals(memberId)) {
            throw new BusinessException("게시글 작성자가 다른 사람입니다..");
        }
    }


    private Borrow createDtoToEntity(CreateBorrowRequestDto createBorrowRequestDto, Long memberId) {
        CreateBorrowDto createBorrowDto = new CreateBorrowDto(createBorrowRequestDto, memberId);
        return Borrow.createBorrow(createBorrowDto);
    }

    private UpdateBorrowDto updateRequestToUpdateDto(UpdateBorrowRequestDto updateBorrowRequestDto) {
        return new UpdateBorrowDto(updateBorrowRequestDto);
    }


    //todo
    // 회원에 대한 검증 책임을 대여 도메인에서 하는 것이 맞나..?
        // 이 검증은 다른 도메인(예를 들면, 커뮤니티, 책 요청)에도 똑같은 코드를 사용한다.
            // 다른 도메인마다 이런 검증 코드를 넣게 되면 코드 중복이 일어난다.
        // 시큐리티 필터에서 멤버가 이미 검증된 후에 컨트롤러로 요청이 오는데 굳이 할 필요가 있을까..?
    @Override
    @Transactional(readOnly = true)
    public void existMember(Long memberId) {
        Optional<Boolean> existMember = memberRepository.checkMemberDeletedByTag(memberId);
        Boolean deletedMemberTag = existMember
                .orElseThrow(() -> new BusinessException("멤버가 존재하지 않습니다."));
        if(deletedMemberTag) {
            throw new BusinessException("탈퇴한 멤버입니다.");
        }
    }

}
