package com.todorian.member.command.domain.model;

import com.todorian.BaseTimeEntity;
import com.todorian.member.command.domain.model.property.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_status_tb")
public class MemberStatus extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public MemberStatus(Long memberId, Status status) {
        this.memberId = memberId;
        this.status = status;
    }
}
