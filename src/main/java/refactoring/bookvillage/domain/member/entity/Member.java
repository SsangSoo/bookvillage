package refactoring.bookvillage.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.audit.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "eamil")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "display")
    private String display;

    @Column(name = "state")
    private MemberState state;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "role")
    private Role role;

    public enum Role {
        ADMIN,
        MEMBER,
        GHOST
    }

    public enum MemberState {
        NEW,
        ACTIVITY,
        QUIT
    }

    public static Member createMember(String email, String name, String display, MemberState state, String imgUrl) {
        return new Member(email, name, display, state, imgUrl);
    }

    @Builder
    private Member(String email, String name, String display, MemberState state, String imgUrl) {
        this.email = email;
        this.name = name;
        this.display = display;
        this.state = state;
        this.imgUrl = imgUrl;
        this.role = Role.MEMBER;
    }
}
