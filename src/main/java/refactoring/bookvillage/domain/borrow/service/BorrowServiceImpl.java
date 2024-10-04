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
    public void createBorrow(CreateBorrowDto createBorrowDto) {
        if(memberRepository.deleteMember(createBorrowDto.getMemberId())) {
            throw new BusinessException("요청을 시도한 회원이 탈퇴한 회원입니다.");
        }
        Borrow borrow = Borrow.createBorrow(createBorrowDto);
        borrowRepository.save(borrow);
    }

    @Override
    public void updateBorrow(UpdateBorrowDto updateBorrowDto, Long borrowId, Long memberId) {
        if (memberRepository.deleteMember(memberId)) {
            throw new BusinessException("요청을 시도한 회원이 탈퇴한 회원입니다.");
        }
        Optional<Borrow> findBorrow = borrowRepository.findById(borrowId);
        Borrow borrow = findBorrow.orElseThrow(() -> new BusinessException("대여 게시글이 존재하지 않습니다."));
        borrow.validation(memberId);
        borrow.update(updateBorrowDto);
    }

    @Override
    public void deleteBorrow(Long borrowId, Long memberId) {
        if (memberRepository.deleteMember(memberId)) {
            throw new BusinessException("요청을 시도한 회원이 탈퇴한 회원입니다.");
        }
        Optional<Borrow> findBorrow = borrowRepository.findById(borrowId);
        Borrow borrow = findBorrow.orElseThrow(() -> new BusinessException("대여 게시글이 존재하지 않습니다."));
        borrow.validation(memberId);
        borrow.delete();
    }




}
