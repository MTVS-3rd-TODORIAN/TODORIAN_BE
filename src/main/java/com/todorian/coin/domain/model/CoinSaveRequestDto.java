package com.todorian.coin.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CoinSaveRequestDto {
    @Setter
    private Long memberId;
    private LocalDateTime coinDateTime;
    private Long coinAmount;
    private CoinReason coinReason; //     "coinReason": "GAME_WIN",

    private Long coinForeignId;
}