package refactoring.bookvillage.domain.borrowcomment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrowcomment.entity.BorrowComment;
import refactoring.bookvillage.domain.borrowcomment.repository.BorrowCommentRepository;
import refactoring.bookvillage.domain.borrowcomment.service.dto.CreateBorrowCommentDto;
import refactoring.bookvillage.domain.borrowcomment.service.dto.UpdateBorrowCommentDto;
import refactoring.bookvillage.domain.member.repository.MemberRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BorrowCommentServiceImpl implements BorrowCommentService {

    private final BorrowCommentRepository borrowCommentRepository;
    private final MemberRepository memberRepository;


    @Override
    public Long create(CreateBorrowCommentDto createBorrowCommentDto) {
        BorrowComment borrowComment = BorrowComment.createBorrowComment(createBorrowCommentDto);
        return borrowCommentRepository.save(borrowComment).getId();
    }

    @Override
    public void update(UpdateBorrowCommentDto updateBorrowCommentDto) {
        BorrowComment borrowComment = borrowCommentRepository.findById(updateBorrowCommentDto.getBorrowId()).orElseThrow();
    }
}
