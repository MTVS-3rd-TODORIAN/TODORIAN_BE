package com.todorian.friendship.command.application.dto;

import lombok.*;

/**
 * Friendship 조회용 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendshipQueryDTO {

    private Long id;          // 관계 ID
    private String requester; // 요청자 닉네임
    private String receiver;  // 요청받는 사람 닉네임
    private String status;    // 현재 상태
}
