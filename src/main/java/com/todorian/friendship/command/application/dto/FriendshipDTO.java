package com.todorian.friendship.command.application.dto;

import com.todorian.friendship.command.domain.model.Friendship;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendshipDTO {

    private Long requesterId;
    private Long receiverId;
    private String status;

    public static FriendshipDTO fromEntity(Friendship friendship) {
        return FriendshipDTO.builder()
                .requesterId(friendship.getRequester().getId())
                .receiverId(friendship.getReceiver().getId())
                .status(friendship.getStatus().name())
                .build();
    }
}
