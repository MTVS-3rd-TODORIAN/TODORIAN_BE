package com.todorian.friendship.command.domain.model;

import com.todorian.member.command.domain.model.Member;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_friendship")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private long friendshipId;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    // Enum 타입을 사용하는 상태 필드
    @Enumerated(EnumType.STRING)  // Enum 값을 문자열로 데이터베이스에 저장
    @Column(name = "friendship_status", nullable = false)
    private FriendshipStatus friendshipStatus;

    // 회원(Member)와의 연관관계 (Many-to-One) 설정
    @ManyToOne
    @JoinColumn(name = "member_id_1", nullable = false)  // 첫 번째 회원
    private Member member1;

    @ManyToOne
    @JoinColumn(name = "member_id_2", nullable = false)  // 두 번째 회원
    private Member member2;

    public Friendship() {}

    public Friendship(long friendshipId, LocalDate createdAt, LocalDate modifiedAt, FriendshipStatus friendshipStatus, Member member1, Member member2) {
        this.friendshipId = friendshipId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.friendshipStatus = friendshipStatus;
        this.member1 = member1;
        this.member2 = member2;
    }

    public long getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(long friendshipId) {
        this.friendshipId = friendshipId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public FriendshipStatus getFriendshipStatus() {
        return friendshipStatus;
    }

    public void setFriendshipStatus(FriendshipStatus friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }

    public Member getMember1() {
        return member1;
    }

    public void setMember1(Member member1) {
        this.member1 = member1;
    }

    public Member getMember2() {
        return member2;
    }

    public void setMember2(Member member2) {
        this.member2 = member2;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "friendshipId=" + friendshipId +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", friendshipStatus=" + friendshipStatus +
                ", member1=" + member1 +
                ", member2=" + member2 +
                '}';
    }
}
