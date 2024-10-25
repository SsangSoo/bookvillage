package refactoring.bookvillage.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.domain.audit.BaseEntity;
import refactoring.bookvillage.global.exception.BusinessException;

import static refactoring.bookvillage.global.exception.BusinessException.ExceptionCode.INVALID_EXCEPTION;

@Getter
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

    @Column(name = "nickname")
    private String nickname;

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
    private Member(String email, String name, String nickname, MemberState state, String imgUrl) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.state = state;
        this.imgUrl = imgUrl;
        this.role = Role.MEMBER;
    }

    public boolean isAdmin() {
        return role.equals(Role.ADMIN);
    }

    public boolean isMember() {
        return role.equals(Role.MEMBER);
    }

    public boolean isGhost() {
        return role.equals(Role.GHOST);
    }

    public boolean isGhostOrMember() {
        return isMember() || isGhost();
    }


    public void deleteValid() {
        if(isDeleteTag()) {
            throw new BusinessException(BusinessException.ExceptionCode.DELETED_MEMBER);
        }
    }

    // 유령이거나, 삭제된 회원이 접근할 경우에 대한 검증
    public void verifyWhetherGhostAndDeletedMember() {
        if(isGhost() || isDeleteTag()) {
            throw new BusinessException(INVALID_EXCEPTION);
        }
    }

}
