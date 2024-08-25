package com.todorian.member.domain;

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
@Table(name = "member_tb")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickName;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;

    @ColumnDefault("'NONE'")
    private SocialType socialType;
    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'USER'")
    private Authority authority;

    @Builder
    public Member(String nickName, String email, String password, SocialType socialType, Authority authority) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.socialType = socialType;
        this.authority = authority;
    }
}
