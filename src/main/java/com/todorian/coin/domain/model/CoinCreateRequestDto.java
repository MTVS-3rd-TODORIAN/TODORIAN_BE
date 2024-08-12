package com.todorian.coin.domain.model;

import java.time.LocalDateTime;

public class CoinCreateRequestDto {

    private LocalDateTime coinDateTime;
    private Long characterId;
    private Long advertisementId;
    private Long gameId;
    private Long itemId;
    private Long givenMemberId;
    private long memberId;
    private long coinAmount;
    private CoinReason coinReason;

    public CoinCreateRequestDto() {
    }

    public CoinCreateRequestDto(LocalDateTime coinDateTime, Long characterId, Long advertisementId,
        Long gameId, Long itemId, Long givenMemberId, long memberId, long coinAmount,
        CoinReason coinReason) {
        this.coinDateTime = coinDateTime;
        this.characterId = characterId;
        this.advertisementId = advertisementId;
        this.gameId = gameId;
        this.itemId = itemId;
        this.givenMemberId = givenMemberId;
        this.memberId = memberId;
        this.coinAmount = coinAmount;
        this.coinReason = coinReason;
    }

    public LocalDateTime getCoinDateTime() {
        return coinDateTime;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getItemId() {
        return itemId;
    }

    public Long getGivenMemberId() {
        return givenMemberId;
    }

    public long getMemberId() {
        return memberId;
    }

    public long getCoinAmount() {
        return coinAmount;
    }

    public CoinReason getCoinReason() {
        return coinReason;
    }

    @Override
    public String toString() {
        return "CoinCreateRequestDto{" +
            "coinDateTime=" + coinDateTime +
            ", characterId=" + characterId +
            ", advertisementId=" + advertisementId +
            ", gameId=" + gameId +
            ", itemId=" + itemId +
            ", givenMemberId=" + givenMemberId +
            ", memberId=" + memberId +
            ", coinAmount=" + coinAmount +
            ", coinReason=" + coinReason +
            '}';
    }
}