package refactoring.bookvillage.domain.borrow.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowListResponse;
import refactoring.bookvillage.domain.borrow.controller.borrowdto.BorrowResponse;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.repository.query.BorrowQueryRepository;
import refactoring.bookvillage.domain.borrow.repository.query.dto.BorrowListQueryDto;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import java.util.List;

import static refactoring.bookvillage.global.exception.BusinessException.ExceptionCode.*;
import static refactoring.bookvillage.global.exception.BusinessException.ExceptionCode.NOT_EXIST_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BorrowQueryServiceImpl implements BorrowQueryService {

    private final MemberRepository memberRepository;
    private final BorrowRepository borrowRepository;
    private final BorrowQueryRepository queryRepository;


    @Override
    public BorrowResponse findBorrow(Long borrowId, Long memberId) {

        Borrow findBorrow = borrowRepository.findById(borrowId).orElseThrow(() -> new BusinessException(NOT_EXIST_CONTENT));

        // 삭제되었지만, 관리자라면 볼 수 있다.
        // 관리자가 아니면 못 본다.
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->  new BusinessException(NOT_EXIST_MEMBER));

        if(findBorrow.isDeleteTag() && member.isGhostOrMember()) { // 삭제되었는데, 멤버 혹은 유령 회원이라면
            throw new BusinessException(DELETED_CONTENT);
        }

        findBorrow.addView(); // 조회수 증가.

        return findBorrow.toResponseDto(memberId, member.isAdmin());

    }

    @Override
    public List<BorrowListResponse> findBorrowList(Long memberId, BorrowCondition condition) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(NOT_EXIST_MEMBER));
        List<BorrowListQueryDto> borrowList = queryRepository.getBorrowList(member.getRole().name(), condition.getKeyword());

        return borrowList.stream()
                .map(BorrowListQueryDto::toResponseDto)
                .toList();
    }
}
