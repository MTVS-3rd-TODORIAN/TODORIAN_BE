package com.todorian.coin;

import com.todorian.coin.domain.model.CoinCreateRequestDto;
import com.todorian.coin.domain.model.CoinReason;
import com.todorian.coin.domain.service.CoinService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoinTests {

    private static final Logger log = LoggerFactory.getLogger(CoinTests.class);
    @Autowired
    private CoinService coinService;

    @DisplayName("coin 내역 생성 테스트")
    @Test
    void testCreateCoin() {

        CoinCreateRequestDto coinCreateRequestDto1 = new CoinCreateRequestDto(
            LocalDateTime.now(),
            1L,
            null,
            null,
            null,
            null,
            1L,
            10L,
            CoinReason.CHARACTER_GROWTH
        );

        CoinCreateRequestDto coinCreateRequestDto2 = new CoinCreateRequestDto(
            LocalDateTime.now(),
            null,
            1L,
            null,
            null,
            null,
            1L,
            5L,
            CoinReason.ADVERTISEMENT_WATCHED
        );

        CoinCreateRequestDto coinCreateRequestDto3 = new CoinCreateRequestDto(
            LocalDateTime.now(),
            null,
            null,
            1L,
            null,
            null,
            1L,
            -5L,
            CoinReason.GAME_CONSUME
        );

        CoinCreateRequestDto coinCreateRequestDto4 = new CoinCreateRequestDto(
            LocalDateTime.now(),
            null,
            null,
            1L,
            null,
            null,
            1L,
            -5L,
            CoinReason.GAME_LOSE
        );

        CoinCreateRequestDto coinCreateRequestDto5 = new CoinCreateRequestDto(
            LocalDateTime.now(),
            null,
            null,
            null,
            1L,
            null,
            1L,
            -30L,
            CoinReason.ITEM_BOUGHT
        );

        CoinCreateRequestDto coinCreateRequestDto6 = new CoinCreateRequestDto(
            LocalDateTime.now(),
            null,
            null,
            null,
            null,
            2L,
            1L,
            10L,
            CoinReason.MEMBER_GIVEN
        );

        Assertions.assertDoesNotThrow(
            () -> coinService.createCoin(coinCreateRequestDto1)
        );

        Assertions.assertDoesNotThrow(
            () -> coinService.createCoin(coinCreateRequestDto2)
        );

        Assertions.assertDoesNotThrow(
            () -> coinService.createCoin(coinCreateRequestDto3)
        );

        Assertions.assertDoesNotThrow(
            () -> coinService.createCoin(coinCreateRequestDto4)
        );

        Assertions.assertDoesNotThrow(
            () -> coinService.createCoin(coinCreateRequestDto5)
        );

        Assertions.assertDoesNotThrow(
            () -> coinService.createCoin(coinCreateRequestDto6)
        );

        log.info("coinCreateRequestDto1: {}", coinCreateRequestDto1);
        log.info("coinCreateRequestDto2: {}", coinCreateRequestDto2);
        log.info("coinCreateRequestDto3: {}", coinCreateRequestDto3);
        log.info("coinCreateRequestDto4: {}", coinCreateRequestDto4);
        log.info("coinCreateRequestDto5: {}", coinCreateRequestDto5);
        log.info("coinCreateRequestDto6: {}", coinCreateRequestDto6);
    }

    @DisplayName("coin 내역 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void testFindById(int coinId) {
        Assertions.assertDoesNotThrow(
            () -> coinService.findById(coinId)
        );
    }
}