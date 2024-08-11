package com.todorian.coin.domain.model;

import java.time.LocalDateTime;

public class CoinFindResponseDto {

    private LocalDateTime coinDateTime;
    private Long characterId;
    private Long advertisementId;
    private Long gameId;
    private Long itemId;
    private Long givenMemberId;
    private long memberId;
    private long coinAmount;
    private CoinReason coinReason;

    public CoinFindResponseDto() {
    }

    public CoinFindResponseDto(Long characterId, LocalDateTime coinDateTime, Long advertisementId,
        Long gameId, Long itemId, Long givenMemberId, long memberId, long coinAmount,
        CoinReason coinReason) {
        this.characterId = characterId;
        this.coinDateTime = coinDateTime;
        this.advertisementId = advertisementId;
        this.gameId = gameId;
        this.itemId = itemId;
        this.givenMemberId = givenMemberId;
        this.memberId = memberId;
        this.coinAmount = coinAmount;
        this.coinReason = coinReason;
    }

    public CoinFindResponseDto(Coin coin) {
        this.coinDateTime = coin.getCoinDateTime();
        this.characterId = coin.getCharacterId();
        this.advertisementId = coin.getAdvertisementId();
        this.gameId = coin.getGameId();
        this.itemId = coin.getItemId();
        this.givenMemberId = coin.getGivenMemberId();
        this.memberId = coin.getMemberId();
        this.coinAmount = coin.getCoinAmount();
        this.coinReason = coin.getCoinReason();
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
        return "CoinFindResponseDto{" +
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