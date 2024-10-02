package refactoring.bookvillage.domain.community.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.audit.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    @Column(name = "cumunity_type")
    private CummunityType type;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "display", length = 100)
    private String display;

    @Column(name = "content")
    private String content;

    @Column(name = "view_count")
    private Long viewCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    enum CummunityType {
        MEET,
        STUDY,
        CUMMUNITY
    }
}
