package refactoring.bookvillage.domain.borrowcomment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrowcomment.controller.dto.BorrowCommentResponse;
import refactoring.bookvillage.domain.borrowcomment.entity.BorrowComment;
import refactoring.bookvillage.domain.borrowcomment.repository.BorrowCommentRepository;
import refactoring.bookvillage.domain.borrowcomment.service.dto.CreateBorrowCommentDto;
import refactoring.bookvillage.domain.borrowcomment.service.dto.UpdateBorrowCommentDto;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import static refactoring.bookvillage.global.exception.BusinessException.ExceptionCode.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BorrowCommentServiceImpl implements BorrowCommentService {

    private final BorrowCommentRepository borrowCommentRepository;
    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;


    @Override
    public BorrowCommentResponse create(CreateBorrowCommentDto createBorrowCommentDto) {
        Member member = memberRepository.findById(createBorrowCommentDto.getMemberId())
                .orElseThrow(() ->  new BusinessException(NOT_EXIST_MEMBER));
        member.verifyWhetherGhostAndDeletedMember();

        BorrowComment borrowComment = BorrowComment.createBorrowComment(createBorrowCommentDto);
        BorrowComment savedBorrowComment = borrowCommentRepository.save(borrowComment);
        return savedBorrowComment.toResponseDto(member.getNickname());
    }

    @Override
    public BorrowCommentResponse update(UpdateBorrowCommentDto updateBorrowCommentDto) {
        Member member = memberRepository.findById(updateBorrowCommentDto.getMemberId())
                .orElseThrow(() ->  new BusinessException(NOT_EXIST_MEMBER));
        member.verifyWhetherGhostAndDeletedMember();

        Borrow borrow = borrowRepository.findById(updateBorrowCommentDto.getBorrowId()).orElseThrow(() -> new BusinessException(NOT_EXIST_CONTENT));
        borrow.isDeleteValid();

        BorrowComment borrowComment = borrowCommentRepository.findById(updateBorrowCommentDto.getBorrowCommentId()).orElseThrow(() -> new BusinessException(NOT_EXIST_COMMENT));
        borrowComment.borrowValid(updateBorrowCommentDto.getBorrowId());
        borrowComment.writerValid(updateBorrowCommentDto.getMemberId());

        borrowComment.update(updateBorrowCommentDto.getComment());
        return borrowComment.toResponseDto(member.getNickname());
    }

    @Override
    public void delete(Long borrowId, Long borrowCommentId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->  new BusinessException(NOT_EXIST_MEMBER));
        member.verifyWhetherGhostAndDeletedMember();

        Borrow borrow = borrowRepository.findById(borrowId).orElseThrow(() -> new BusinessException(NOT_EXIST_CONTENT));
        borrow.isDeleteValid();

        BorrowComment borrowComment = borrowCommentRepository.findById(borrowCommentId).orElseThrow(() -> new BusinessException(NOT_EXIST_COMMENT));
        borrowComment.borrowValid(borrowId);
        borrowComment.writerValid(borrowId);
        borrowComment.isDeleteValid();

        borrowComment.delete();
    }


}
