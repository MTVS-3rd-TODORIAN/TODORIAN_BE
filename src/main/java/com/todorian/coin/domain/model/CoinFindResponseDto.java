package com.todorian.coin.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoinFindResponseDto {
    private Long coinId;
    private Long memberId;
    private LocalDateTime coinDateTime;
    private Long coinAmount;
    private CoinReason coinReason;
    private Long coinForeignId;
}