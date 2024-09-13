package refactoring.bookvillage.domain.communitycomment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_comment_id")
    private Long id;

    @Column(name = "display", length = 100)
    private String display;

    @Column(name = "content")
    private String content;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "community_id")
    private Long communityId;

}
