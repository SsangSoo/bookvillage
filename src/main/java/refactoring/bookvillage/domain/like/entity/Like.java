package refactoring.bookvillage.domain.like.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.community.entity.Community;
import refactoring.bookvillage.domain.member.entity.Member;

import static lombok.AccessLevel.*;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "community_id")
    private Long communityId;
}
