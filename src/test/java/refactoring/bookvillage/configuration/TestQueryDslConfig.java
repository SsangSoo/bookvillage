package refactoring.bookvillage.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import refactoring.bookvillage.domain.borrow.repository.query.BorrowQueryRepository;
import refactoring.bookvillage.domain.borrow.repository.query.BorrowQueryRepositoryImpl;

@TestConfiguration
public class TestQueryDslConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public BorrowQueryRepository borrowQueryRepository() {
        return new BorrowQueryRepositoryImpl(em);
    }
}
