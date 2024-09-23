package com.todorian.coin.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CoinSaveRequestDto {
    private Long memberId;
    private LocalDateTime coinDateTime;
    private Long coinAmount;
    private CoinReason coinReason;
    private Long coinForeignId;
}