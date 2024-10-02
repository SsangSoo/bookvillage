package refactoring.bookvillage.domain.book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.audit.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "isbn", length = 100)
    private String isbn;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "average")
    private Double average;

    @Column(name = "thumbnail", columnDefinition = "text")
    private String thumbnail;


}
