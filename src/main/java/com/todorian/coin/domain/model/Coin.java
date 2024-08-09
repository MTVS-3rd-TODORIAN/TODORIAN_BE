package com.todorian.coin.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * 코인의 내역을 나타내기 위한 테이블.
 * - 언제 얻었는가 (LocalDateTime)
 * <p>
 * - 어디에서 얻었는가 (FK-ID)
 * <p>
 * - 누가 얻었는가 ()
 * <p>
 * - 어떻게 얻었는가
 * <p>
 * - 왜얻었는가
 */
@Entity
@Table(name = "tbl_coin")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coin_id")
    private long coinId;

    /**
     * 언제? : 코인을 획득하거나 사용한 날짜 및 시간
     */
    @Column(name = "coin_date_time")
    private LocalDateTime coinDateTime;

    /**
     * 어디서? : 코인의 구체적인 출처를 나타내는 컬럼. 각각의 필드는 nullable 함.
     */
    @Column(name = "character_id")
    private Long characterId;

    @Column(name = "advertisement_id")
    private Long advertisementId;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "given_member_id")
    private Long givenMemberId;

    /**
     * 누가? : 받는 사람의 아이디
     */
    @Column(name = "member_id")
    private long memberId;

    /**
     * 얼마나? : 획득한 코인의 양을 나타내는 컬럼. 양수 뿐만 아니라 음수까지 표현할 수 있음. 소수점 단위는 표현되지 않음에 주의. BigInteger 또한 고려해 볼
     * 만한 듯.
     */
    @Column(name = "coin_amount")
    private long coinAmount;

    /**
     * 왜? : 코인을 받게 된 계기. ex) 게임 승리, 게임 패배, 관리자 부여 등. Enum Type 으로 관리할 것.
     */
    @Column(name = "coin_reason")
    private CoinReason coinReason;

    public Coin() {
    }

    public Coin(LocalDateTime coinDateTime, Long characterId, Long advertisementId, Long gameId,
        Long itemId, Long givenMemberId, long memberId, long coinAmount, CoinReason coinReason) {
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

    public long getCoinId() {
        return coinId;
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
        return "Coin{" +
            "coinId=" + coinId +
            ", coinDateTime=" + coinDateTime +
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