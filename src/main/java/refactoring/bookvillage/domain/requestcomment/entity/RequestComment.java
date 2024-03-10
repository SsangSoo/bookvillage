package refactoring.bookvillage.domain.requestcomment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.request.entity.Request;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_comment_id")
    private Long id;

    @Column(name = "display", length = 100)
    private String display;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
