package refactoring.bookvillage.domain.borrow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
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


    private Borrow dtoToEntity(CreateBorrowRequestDto createBorrowRequestDto, Long memberId) {
        CreateBorrowDto createBorrowDto = new CreateBorrowDto(createBorrowRequestDto, memberId);
        return Borrow.createBorrow(createBorrowDto);
    }

    private void existMember(Long memberId) {
        if(!memberRepository.existsMemberById(memberId)) {
            throw new BusinessException("멤버가 존재하지 않습니다.");
        }
    }
}
