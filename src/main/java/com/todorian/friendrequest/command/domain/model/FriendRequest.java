package com.todorian.friendrequest.command.domain.model;

import com.todorian.member.command.domain.model.Member;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "friend_request")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private Member requester;  // 친구 요청을 보낸 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;  // 친구 요청을 받은 회원

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private FriendRequestStatus requestStatus;  // 요청 상태 (PENDING, ACCEPTED, REJECTED)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public FriendRequest() {}

    // 생성자: 필수 필드를 초기화하는 생성자
    public FriendRequest(Member requester, Member receiver) {
        if (requester == null || receiver == null) {
            throw new IllegalArgumentException("Requester and receiver cannot be null");
        }
        if (requester.equals(receiver)) {
            throw new IllegalArgumentException("Requester and receiver cannot be the same.");
        }
        this.requester = requester;
        this.receiver = receiver;
        this.createdAt = LocalDateTime.now();
        this.requestStatus = FriendRequestStatus.PENDING;  // 기본 상태는 PENDING
    }

    // 상태 변경 메서드 (데이터 상태만 변경)
    public void changeStatus(FriendRequestStatus newStatus) {
        this.requestStatus = newStatus;
        this.modifiedAt = LocalDateTime.now();  // 수정 시간 기록
    }

    public Long getRequestId() {
        return requestId;
    }

    public Member getRequester() {
        return requester;
    }

    public Member getReceiver() {
        return receiver;
    }

    public FriendRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "requestId=" + requestId +
                ", requester=" + requester +
                ", receiver=" + receiver +
                ", requestStatus=" + requestStatus +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
