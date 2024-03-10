package refactoring.bookvillage.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import refactoring.bookvillage.global.audit.BaseEntity;

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

    @Column(name = "display")
    private String display;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state")
    private MemberState state;

    @Column(name = "img_url")
    private String imgUrl;

}
