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


    @Override
    @Transactional(readOnly = true)
    public void existMember(Long memberId) {
        if(!memberRepository.existsMemberById(memberId)) {
            throw new BusinessException("멤버가 존재하지 않습니다.");
        }
        if(memberRepository.checkDeletedByTag(memberId)) {
            throw new BusinessException("탈퇴한 멤버입니다.");
        }
    }

}
