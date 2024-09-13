package refactoring.bookvillage.domain.borrow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import refactoring.bookvillage.domain.borrow.controller.dto.BorrowResponseDto;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;

    @Override
    public BorrowResponseDto createBorrow(CreateBorrowRequestDto createBorrowRequestDto) {

    }
}
