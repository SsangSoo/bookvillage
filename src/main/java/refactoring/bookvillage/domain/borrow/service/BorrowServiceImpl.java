package refactoring.bookvillage.domain.borrow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.UpdateBorrowDto;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import java.util.Optional;

import static refactoring.bookvillage.global.exception.BusinessException.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createBorrow(CreateBorrowDto createBorrowDto) {
        Borrow borrow = Borrow.createBorrow(createBorrowDto);
        borrowRepository.save(borrow);
    }

    @Override
    public void updateBorrow(UpdateBorrowDto updateBorrowDto, Long borrowId, Long memberId) {
        Optional<Borrow> findBorrow = borrowRepository.findById(borrowId);
        Borrow borrow = findBorrow.orElseThrow(() -> new BusinessException(NO_BORROW));

        borrow.validation(memberId);
        borrow.update(updateBorrowDto);
    }

    @Override
    public void deleteBorrow(Long borrowId, Long memberId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BusinessException(NO_BORROW));
        borrow.validation(memberId);
        borrow.delete();
    }

    @Override
    @Transactional(readOnly = true)
    public BorrowResponseDto getBorrow(Long borrowId, Long memberId) {
        borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BusinessException(NO_CONTENT));
        return null;


    }


}