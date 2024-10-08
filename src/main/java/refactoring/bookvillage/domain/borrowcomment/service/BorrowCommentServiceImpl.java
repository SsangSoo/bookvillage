package refactoring.bookvillage.domain.borrowcomment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrowcomment.service.dto.CreateBorrowCommentDto;

@Slf4j
@Service
@Transactional
public class BorrowCommentServiceImpl implements BorrowCommentService {

    @Override
    public void create(CreateBorrowCommentDto createBorrowCommentDto) {

    }
}
