package refactoring.bookvillage.domain.borrow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowCondition;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowListResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
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
import java.util.Optional;

import static refactoring.bookvillage.global.exception.BusinessException.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowServiceImpl implements BorrowService {

    private final MemberRepository memberRepository;
    private final BorrowRepository borrowRepository;
    private final BorrowQueryRepository queryRepository;

    @Override
    public void createBorrow(CreateBorrowDto createBorrowDto) {
        Borrow borrow = Borrow.createBorrow(createBorrowDto);
        borrowRepository.save(borrow);
    }

    @Override
    public void updateBorrow(UpdateBorrowDto updateBorrowDto, Long borrowId, Long memberId) {
        Optional<Borrow> findBorrow = borrowRepository.findById(borrowId);
        Borrow borrow = findBorrow.orElseThrow(() -> new BusinessException(NO_BORROW));

        borrow.deleteWhetherValidation();
        borrow.otherWriterAccessVerify(memberId);
        borrow.update(updateBorrowDto);
    }

    @Override
    public void deleteBorrow(Long borrowId, Long memberId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BusinessException(NO_BORROW));
        borrow.deleteWhetherValidation();
        borrow.otherWriterAccessVerify(memberId);
        borrow.delete();
    }

    @Override
    @Transactional(readOnly = true)
    public BorrowResponseDto getBorrow(Long borrowId, Long memberId) {
        Borrow findBorrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BusinessException(NO_CONTENT));

        // 삭제되었지만, 관리자라면 볼 수 있다.
        // 관리자가 아니면 못 본다.
        boolean deleteTag = findBorrow.isDeleteTag();

        String memberRole = memberRepository.findMemberRoleById(memberId);

        boolean isNotAdmin = isAdmin(memberRole);
        if(deleteTag && isNotAdmin) {
            throw new BusinessException(DELETED_CONTENT);
        }
        findBorrow.addView(); // 조회수 증가.
        return findBorrow.toResponseDto(memberId, memberRole);

    }

    @Override
    @Transactional(readOnly = true)
    public List<BorrowListResponseDto> getBorrowList(Long memberId, BorrowCondition condition) {
        String memberRole = memberRepository.findMemberRoleById(memberId);
        List<BorrowListQueryDto> borrowList = queryRepository.getBorrowList(memberRole, condition.getKeyword());

        return borrowList.stream()
                .map(BorrowListQueryDto::toResponseDto)
                .toList();
    }

    private boolean isAdmin(String memberRole) {
        return !memberRole.equals(Member.Role.ADMIN.name());
    }


}