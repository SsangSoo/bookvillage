package refactoring.bookvillage.domain.request.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "display")
    private String display;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "member_id")
    private Long memberId;


}

