package refactoring.bookvillage.domain.borrow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequest;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createBorrow(CreateBorrowRequestDto createBorrowRequestDto, Long memberId) {
        existMember(memberId);
        Borrow borrow = dtoToEntity(createBorrowRequestDto, memberId);
        borrowRepository.save(borrow);
    }

    @Override
    public void updateBorrow(UpdateBorrowRequest updateBorrowRequestDto, Long borrowId, Long memberId) {
        existMember(memberId);
        existBorrow(borrowId);

    }




    private Borrow dtoToEntity(CreateBorrowRequestDto createBorrowRequestDto, Long memberId) {
        CreateBorrowDto createBorrowDto = new CreateBorrowDto(createBorrowRequestDto, memberId);
        return Borrow.createBorrow(createBorrowDto);
    }


    @Override
    @Transactional(readOnly = true)
    public void existMember(Long memberId) {
        if(!memberRepository.existsMemberById(memberId)) {
            throw new BusinessException("멤버가 존재하지 않습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void existBorrow(Long borrowId) {
        if(!borrowRepository.existsBorrowById(borrowId)) {
            throw new BusinessException("대여 게시글이 존재하지 않습니다.");
        }
    }
}
