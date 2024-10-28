package refactoring.bookvillage.domain.borrow.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowListResponse;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowResponse;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.UpdateBorrowDto;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import static refactoring.bookvillage.global.exception.BusinessException.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowCommandServiceImpl implements BorrowCommandService {

    private final MemberRepository memberRepository;
    private final BorrowRepository borrowRepository;

    @Override
    public BorrowResponse create(CreateBorrowDto createBorrowDto) {
        Member member = memberRepository.findById(createBorrowDto.getMemberId())
                .orElseThrow(() -> new BusinessException(NOT_EXIST_MEMBER));

        member.verifyWhetherGhostAndDeletedMember();

        Borrow borrow = Borrow.createBorrow(createBorrowDto);
        Borrow savedBorrow = borrowRepository.save(borrow);

        return savedBorrow.toResponseDto(member.getId(), member.isAdmin());
    }

    @Override
    public BorrowResponse update(UpdateBorrowDto updateBorrowDto) {
        Member member = memberRepository.findById(updateBorrowDto.getMemberId())
                .orElseThrow(() -> new BusinessException(NOT_EXIST_MEMBER));

        member.verifyWhetherGhostAndDeletedMember();

        Borrow borrow = borrowRepository.findById(updateBorrowDto.getBorrowId()).orElseThrow(() -> new BusinessException(NO_CONTENT));
        borrow.isDeleteValid();
        borrow.otherWriterAccessVerify(updateBorrowDto.getMemberId());
        borrow.update(updateBorrowDto);

        return borrow.toResponseDto(member.getId(), member.isAdmin());

    }

    @Override
    public void delete(Long borrowId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(NOT_EXIST_MEMBER));

        member.verifyWhetherGhostAndDeletedMember();

        Borrow borrow = borrowRepository.findById(borrowId).orElseThrow(() -> new BusinessException(NOT_EXIST_CONTENT));
        borrow.isDeleteValid();
        borrow.otherWriterAccessVerify(memberId);
        borrow.delete();
    }






}