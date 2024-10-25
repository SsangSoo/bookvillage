package refactoring.bookvillage.domain.borrow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowListResponse;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponse;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.repository.query.BorrowQueryRepository;
import refactoring.bookvillage.domain.borrow.repository.query.dto.BorrowListQueryDto;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.service.dto.UpdateBorrowDto;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import java.util.List;

import static refactoring.bookvillage.global.exception.BusinessException.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowServiceImpl implements BorrowService {

    private final MemberRepository memberRepository;
    private final BorrowRepository borrowRepository;
    private final BorrowQueryRepository queryRepository;

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

    //todo
    // 댓글 기능 추가해야 함.
    @Override
    @Transactional(readOnly = true)
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

        // 댓글도 가져와야함.
        // return 에 댓글도 넣어야 함.
        return findBorrow.toResponseDto(memberId, member.isAdmin());

    }

    @Override
    @Transactional(readOnly = true)
    public List<BorrowListResponse> findBorrowList(Long memberId, BorrowCondition condition) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(NOT_EXIST_MEMBER));
        List<BorrowListQueryDto> borrowList = queryRepository.getBorrowList(member.getRole().name(), condition.getKeyword());

        return borrowList.stream()
                .map(BorrowListQueryDto::toResponseDto)
                .toList();
    }




}