package com.todorian.member.command.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String nickName;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'NONE'")
    private SocialType socialType;
    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'USER'")
    private Authority authority;
    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private Status status;

    @Builder
    public Member(String nickName, String email, String password, SocialType socialType, Authority authority, Status status) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.socialType = socialType;
        this.authority = authority;
        this.status = status;
    }

    public void activate() {
        this.status = Status.ACTIVE;
    }

    public void deactivate() {
        this.status = Status.DEACTIVATED;
    }

}
