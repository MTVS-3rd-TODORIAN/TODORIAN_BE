package com.todorian.friendship.command.domain.model;

public enum FriendshipStatus {
    PENDING,  // 친구 요청 대기 중
    ACTIVE,   // 친구 관계 활성 상태
    BLOCKED,  // 친구 관계 차단됨
    REMOVED   // 친구 관계 삭제됨
}
