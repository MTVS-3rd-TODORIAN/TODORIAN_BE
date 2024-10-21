package com.todorian.coin.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tbl_coin")
@NoArgsConstructor
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coinId;

    private Long memberId;

    private LocalDateTime coinDateTime;

    private Long coinAmount;

    private CoinReason coinReason;

    private Long coinForeignId;

    @Builder
    public Coin(Long memberId, LocalDateTime coinDateTime, Long coinAmount, CoinReason coinReason, Long coinForeignId) {
        this.memberId = memberId;
        this.coinDateTime = (coinDateTime != null) ? coinDateTime : LocalDateTime.now();
        this.coinAmount = coinAmount;
        this.coinReason = coinReason;
        this.coinForeignId = coinForeignId;
    }
}