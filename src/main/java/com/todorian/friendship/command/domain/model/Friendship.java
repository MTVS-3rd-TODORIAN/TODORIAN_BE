package com.todorian.friendship.command.domain.model;

import com.todorian.member.command.domain.model.Member;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_friendship")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private long friendshipId;  // 자동 생성되는 ID

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // 관계 생성 시간

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;  // 관계 수정 시간

    // Enum 타입을 사용하는 상태 필드 (ACTIVE 활성, BLOCKED 차단 , REMOVED 삭제)
    @Enumerated(EnumType.STRING)
    @Column(name = "friendship_status", nullable = false)
    private FriendshipStatus friendshipStatus;

    // 회원(Member)와의 연관관계 설정 (Many-to-One 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id_1", nullable = false)
    private Member member1;  // 첫 번째 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id_2", nullable = false)
    private Member member2;  // 두 번째 회원

    public Friendship() {}

    // 필수 필드를 초기화하는 생성자
    public Friendship(Member member1, Member member2) {
        if (member1 == null || member2 == null) {
            throw new IllegalArgumentException("Members cannot be null");
        }
        if (member1.equals(member2)) {
            throw new IllegalArgumentException("Members cannot be the same.");
        }
        this.member1 = member1;
        this.member2 = member2;
        this.createdAt = LocalDateTime.now();
        this.friendshipStatus = FriendshipStatus.ACTIVE;  // 기본 상태는 ACTIVE
    }

    public long getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(long friendshipId) {
        this.friendshipId = friendshipId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
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
